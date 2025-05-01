package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.BadgeRequest;
import com.focusgrow.backend.dto.BadgeResponse;
import com.focusgrow.backend.entity.Badge;
import com.focusgrow.backend.entity.User;
import com.focusgrow.backend.repository.BadgeRepository;
import com.focusgrow.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;

    @Override
    public List<BadgeResponse> getBadgesByUserId(Integer userId) {
        List<Badge> badges = badgeRepository.findByUserId(userId);
        return badges.stream()
                .map(badge -> new BadgeResponse(badge.getId(), badge.getUser().getId(), badge.getBadgeType(), badge.getEarnedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BadgeResponse> getAllBadges() {
        // 예시로 전체 뱃지를 가져오는 방식
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(badge -> new BadgeResponse(badge.getId(), badge.getUser().getId(), badge.getBadgeType(), badge.getEarnedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public BadgeResponse earnBadge(Integer userId, BadgeRequest badgeRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Badge badge = Badge.builder()
                .user(user)
                .badgeType(badgeRequest.getBadgeType())
                .earnedAt(LocalDateTime.now())
                .build();

        badge = badgeRepository.save(badge);

        return new BadgeResponse(badge.getId(), badge.getUser().getId(), badge.getBadgeType(), badge.getEarnedAt());
    }
}