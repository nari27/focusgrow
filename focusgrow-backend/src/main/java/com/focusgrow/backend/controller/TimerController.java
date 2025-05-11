package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.TimerRequest;
import com.focusgrow.backend.dto.TimerResponse;
import com.focusgrow.backend.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timer")
@RequiredArgsConstructor
public class TimerController {

    private final TimerService timerService;

    @PostMapping("/start")
    public ResponseEntity<TimerResponse> startTimer(@RequestBody TimerRequest request) {
        return ResponseEntity.ok(timerService.startTimer(request));
    }

    @PostMapping("/stop")
    public ResponseEntity<TimerResponse> stopTimer(@RequestBody TimerRequest request) {
        return ResponseEntity.ok(timerService.stopTimer(request));
    }

    @GetMapping("/status")
    public ResponseEntity<TimerResponse> getCurrentTimerStatus(@PathVariable Integer userId) {
        return ResponseEntity.ok(timerService.getCurrentTimerStatus(userId));
    }
}