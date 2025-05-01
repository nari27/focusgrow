package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.PlantRequest;
import com.focusgrow.backend.dto.PlantResponse;
import com.focusgrow.backend.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    // 내 식물 정보 조회
    @GetMapping("/users/{id}/plant")
    public ResponseEntity<PlantResponse> getPlantByUserId(@PathVariable Integer id) {
        PlantResponse response = plantService.getPlantByUserId(id);
        return ResponseEntity.ok(response);
    }

    // 식물 성장 처리 (EXP 누적)
    @PostMapping("/plant/grow")
    public ResponseEntity<PlantResponse> growPlant(@RequestBody PlantRequest request) {
        PlantResponse response = plantService.growPlant(request);
        return ResponseEntity.ok(response);
    }

    // 식물 레벨업 여부 확인 및 처리
    @PostMapping("/plant/level-up")
    public ResponseEntity<PlantResponse> levelUpPlant(@RequestBody Integer plantId) {
        PlantResponse response = plantService.checkAndLevelUp(plantId);
        return ResponseEntity.ok(response);
    }
}
