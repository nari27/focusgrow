package com.focusgrow.backend.service.impl;

import com.focusgrow.backend.dto.TimerRequest;
import com.focusgrow.backend.dto.TimerResponse;
import com.focusgrow.backend.entity.Timer;
import com.focusgrow.backend.entity.TimerStatus;
import com.focusgrow.backend.entity.User;
import com.focusgrow.backend.repository.TimerRepository;
import com.focusgrow.backend.repository.UserRepository;
import com.focusgrow.backend.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService {

    private final TimerRepository timerRepository;
    private final UserRepository userRepository;

    @Override
    public TimerResponse startTimer(TimerRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Timer timer = Timer.builder()
                .user(user)
                .startTime(request.getStartTime())
                .status(TimerStatus.IN_PROGRESS)
                .build();

        Timer saved = timerRepository.save(timer);

        return TimerResponse.builder()
                .timerId(saved.getId())
                .userId(saved.getUser().getId())
                .startTime(saved.getStartTime())
                .status(saved.getStatus())
                .build();
    }

    @Override
    public TimerResponse stopTimer(TimerRequest request) {
        Timer timer = timerRepository.findTopByUserIdAndStatusOrderByCreatedAtDesc(
                        request.getUserId(), TimerStatus.IN_PROGRESS)
                .orElseThrow(() -> new RuntimeException("No active timer found"));

        timer.setEndTime(request.getEndTime());
        int duration = (int) Duration.between(timer.getStartTime(), timer.getEndTime()).getSeconds();
        timer.setFocusDuration(duration);
        timer.setStatus(TimerStatus.COMPLETED);
        Timer updated = timerRepository.save(timer);

        return TimerResponse.builder()
                .timerId(updated.getId())
                .userId(updated.getUser().getId())
                .startTime(updated.getStartTime())
                .endTime(updated.getEndTime())
                .focusDuration(updated.getFocusDuration())
                .status(updated.getStatus())
                .build();
    }

    @Override
    public TimerResponse getCurrentTimerStatus(Integer userId) {
        Optional<Timer> optionalTimer = timerRepository.findTopByUserIdAndStatusOrderByCreatedAtDesc(
                userId, TimerStatus.IN_PROGRESS);

        if (optionalTimer.isEmpty()) {
            return null;
        }

        Timer timer = optionalTimer.get();
        return TimerResponse.builder()
                .timerId(timer.getId())
                .userId(timer.getUser().getId())
                .startTime(timer.getStartTime())
                .status(timer.getStatus())
                .build();
    }
}