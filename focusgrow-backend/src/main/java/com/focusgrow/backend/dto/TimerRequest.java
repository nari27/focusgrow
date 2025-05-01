package com.focusgrow.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimerRequest {
    private Integer userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}