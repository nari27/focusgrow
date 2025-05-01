package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.BadgeRequest;
import com.focusgrow.backend.dto.BadgeResponse;
import com.focusgrow.backend.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    // 획득 가능한 뱃지 목록 조회 (전체 뱃지 목록)
    @GetMapping
    public ResponseEntity<List<BadgeResponse>> getAllBadges() {
        List<BadgeResponse> badges = badgeService.getAllBadges();
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }

    // 내가 획득한 뱃지 목록 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<List<BadgeResponse>> getBadgesByUserId(@PathVariable Integer id) {
        List<BadgeResponse> badges = badgeService.getBadgesByUserId(id);
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }

    // 뱃지 획득 처리
    @PostMapping("/users/{id}")
    public ResponseEntity<BadgeResponse> earnBadge(@PathVariable Integer id, @RequestBody BadgeRequest badgeRequest) {
        BadgeResponse badgeResponse = badgeService.earnBadge(id, badgeRequest);
        return new ResponseEntity<>(badgeResponse, HttpStatus.CREATED);
    }
}
