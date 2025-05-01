package com.focusgrow.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FocusLogRequest {
    private Integer userId;
    private Integer focusTime;
    private LocalDate date;
    private Integer plantId;
}
