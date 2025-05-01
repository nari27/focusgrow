package com.focusgrow.backend.dto;

import com.focusgrow.backend.entity.TimerStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TimerResponse {
    private Integer timerId;
    private Integer userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer focusDuration;
    private TimerStatus status;
}
