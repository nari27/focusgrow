package com.focusgrow.backend.repository;

import com.focusgrow.backend.entity.Timer;
import com.focusgrow.backend.entity.TimerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimerRepository extends JpaRepository<Timer, Integer> {
    Optional<Timer> findTopByUserIdAndStatusOrderByCreatedAtDesc(Integer userId, TimerStatus status);
}
