package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.LevelResponse;
import com.focusgrow.backend.entity.FocusRecord;
import com.focusgrow.backend.entity.Level;
import com.focusgrow.backend.repository.FocusRecordRepository;
import com.focusgrow.backend.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final FocusRecordRepository focusRecordRepository;

    // 레벨 기준 리스트 조회
    @Override
    public List<LevelResponse> getAllLevels() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(level -> LevelResponse.builder()
                        .level(level.getLevel())
                        .experiencePoints(level.getExperiencePoints())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int calculateUserLevel(Integer userId) {
        List<FocusRecord> records = focusRecordRepository.findByUserId(userId);
        int totalSeconds = records.stream()
                .mapToInt(FocusRecord::getFocusTime)
                .sum();
        return totalSeconds / 1800; // 예: 30분마다 1레벨 상승
    }

}
