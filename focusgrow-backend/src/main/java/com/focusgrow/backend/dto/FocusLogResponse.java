package com.focusgrow.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class FocusLogResponse {
    private Integer id;
    private Integer userId;
    private Integer focusTime;
    private LocalDate date;
    private Integer plantId;
    private LocalDateTime createdAt;
}
