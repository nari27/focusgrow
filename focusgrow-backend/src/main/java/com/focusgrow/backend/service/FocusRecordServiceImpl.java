package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.FocusLogRequest;
import com.focusgrow.backend.dto.FocusLogResponse;
import com.focusgrow.backend.entity.FocusRecord;
import com.focusgrow.backend.entity.Plant;
import com.focusgrow.backend.entity.User;
import com.focusgrow.backend.repository.FocusRecordRepository;
import com.focusgrow.backend.repository.PlantRepository;
import com.focusgrow.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FocusRecordServiceImpl implements FocusRecordService {

    private final FocusRecordRepository focusRecordRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;

    // 집중 시간 기록 등록
    @Override
    public FocusLogResponse addFocusRecord(FocusLogRequest focusLogRequest) {
        User user = userRepository.findById(focusLogRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Plant plant = plantRepository.findById(focusLogRequest.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        FocusRecord focusRecord = FocusRecord.builder()
                .user(user)
                .focusTime(focusLogRequest.getFocusTime())
                .date(focusLogRequest.getDate())
                .plant(plant)
                .createdAt(LocalDateTime.now())
                .build();

        FocusRecord savedRecord = focusRecordRepository.save(focusRecord);

        return FocusLogResponse.builder()
                .id(savedRecord.getId())
                .userId(savedRecord.getUser().getId())
                .focusTime(savedRecord.getFocusTime())
                .date(savedRecord.getDate())
                .plantId(savedRecord.getPlant().getId())
                .createdAt(savedRecord.getCreatedAt())
                .build();
    }

    // 특정 날짜별 집중 기록 조회
    @Override
    public List<FocusLogResponse> getFocusRecordsByDate(LocalDate date) {
        List<FocusRecord> focusRecords = focusRecordRepository.findByDate(date);
        return focusRecords.stream()
                .map(record -> FocusLogResponse.builder()
                        .id(record.getId())
                        .userId(record.getUser().getId())
                        .focusTime(record.getFocusTime())
                        .date(record.getDate())
                        .plantId(record.getPlant().getId())
                        .createdAt(record.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 집중 시간 통계 (주간/월간)
    @Override
    public Object getFocusStats(String period) {
        // 간단히 예시로 주간 통계
        if ("weekly".equalsIgnoreCase(period)) {
            return focusRecordRepository.getWeeklyStats();
        } else if ("monthly".equalsIgnoreCase(period)) {
            return focusRecordRepository.getMonthlyStats();
        }
        throw new IllegalArgumentException("Invalid period");
    }
}
