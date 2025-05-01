package com.focusgrow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeResponse {
    private Integer id;
    private Integer userId;
    private String badgeType;
    private LocalDateTime earnedAt;
}
