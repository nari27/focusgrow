package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.PlantRequest;
import com.focusgrow.backend.dto.PlantResponse;

public interface PlantService {

    PlantResponse getPlantByUserId(Integer userId);

    PlantResponse growPlant(PlantRequest request);

    PlantResponse checkAndLevelUp(Integer plantId);
}
