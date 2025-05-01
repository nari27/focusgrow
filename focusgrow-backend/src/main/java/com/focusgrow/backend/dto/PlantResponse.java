package com.focusgrow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantResponse {

    private Integer id;               // 식물 ID
    private String name;              // 식물 이름
    private Integer growthLevel;      // 성장 레벨
    private Integer growthDuration;   // 성장 시간 (예시로 추가)
}
