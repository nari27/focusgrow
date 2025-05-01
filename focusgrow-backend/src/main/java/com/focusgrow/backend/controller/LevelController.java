package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.LevelResponse;
import com.focusgrow.backend.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    // 레벨 기준 리스트 조회
    @GetMapping
    public ResponseEntity<List<LevelResponse>> getAllLevels() {
        List<LevelResponse> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }
}
