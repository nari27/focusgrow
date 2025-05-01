package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.FocusLogRequest;
import com.focusgrow.backend.dto.FocusLogResponse;
import com.focusgrow.backend.service.FocusRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/focus-records")
@RequiredArgsConstructor
public class FocusRecordController {

    private final FocusRecordService focusRecordService;

    // 집중 시간 기록 등록
    @PostMapping
    public ResponseEntity<FocusLogResponse> addFocusRecord(@RequestBody FocusLogRequest focusLogRequest) {
        FocusLogResponse response = focusRecordService.addFocusRecord(focusLogRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 특정 날짜별 집중 기록 조회
    @GetMapping
    public ResponseEntity<List<FocusLogResponse>> getFocusRecordsByDate(@RequestParam LocalDate date) {
        List<FocusLogResponse> response = focusRecordService.getFocusRecordsByDate(date);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 집중 시간 통계 (주간/월간)
    @GetMapping("/stats")
    public ResponseEntity<Object> getFocusStats(@RequestParam String period) {
        Object response = focusRecordService.getFocusStats(period);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
