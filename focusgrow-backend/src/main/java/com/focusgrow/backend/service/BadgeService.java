package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.BadgeRequest;
import com.focusgrow.backend.dto.BadgeResponse;
import java.util.List;

public interface BadgeService {
    List<BadgeResponse> getBadgesByUserId(Integer userId);
    List<BadgeResponse> getAllBadges();
    BadgeResponse earnBadge(Integer userId, BadgeRequest badgeRequest);
}
