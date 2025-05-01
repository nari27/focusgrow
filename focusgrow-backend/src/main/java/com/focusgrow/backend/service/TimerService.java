package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.TimerRequest;
import com.focusgrow.backend.dto.TimerResponse;

public interface TimerService {
    TimerResponse startTimer(TimerRequest request);
    TimerResponse stopTimer(TimerRequest request);
    TimerResponse getCurrentTimerStatus(Integer userId);
}
