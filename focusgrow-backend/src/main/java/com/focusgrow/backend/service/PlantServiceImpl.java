package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.PlantRequest;
import com.focusgrow.backend.dto.PlantResponse;
import com.focusgrow.backend.entity.Plant;
import com.focusgrow.backend.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    @Override
    public PlantResponse getPlantByUserId(Integer userId) {
        Plant plant = plantRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        return new PlantResponse(plant.getId(), plant.getName(), plant.getGrowthLevel(), plant.getGrowthDuration());
    }

    @Override
    public PlantResponse growPlant(PlantRequest request) {
        Plant plant = plantRepository.findById(request.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        plant.setGrowthLevel(plant.getGrowthLevel() + request.getExp());  // EXP 누적
        plant.setGrowthDuration(plant.getGrowthDuration() + 1);  // 성장 시간 증가 (예시)

        // 레벨업 체크 후 레벨업 처리
        if (plant.getGrowthLevel() >= 100) {  // 예시 기준: EXP 100 이상이면 레벨업
            plant.setGrowthLevel(0);  // 레벨업 후 EXP 초기화
            // 추가적인 레벨업 로직 (예: 레벨 +1)
        }

        plantRepository.save(plant);

        return new PlantResponse(plant.getId(), plant.getName(), plant.getGrowthLevel(), plant.getGrowthDuration());
    }

    @Override
    public PlantResponse checkAndLevelUp(Integer plantId) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        if (plant.getGrowthLevel() >= 100) {  // 예시: EXP 100 이상이면 레벨업
            plant.setGrowthLevel(0);  // EXP 초기화 후 레벨업
            // 레벨업 후 추가 작업 (예: 레벨+1, 성장 레벨 초기화 등)
        }

        plantRepository.save(plant);

        return new PlantResponse(plant.getId(), plant.getName(), plant.getGrowthLevel(), plant.getGrowthDuration());
    }
}
