package com.focusgrow.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantRequest {

    private Integer plantId;  // 식물 ID
    private Integer exp;      // 누적할 EXP
}
