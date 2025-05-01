package com.focusgrow.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LevelResponse {
    private Integer level;
    private Integer experiencePoints;
}
