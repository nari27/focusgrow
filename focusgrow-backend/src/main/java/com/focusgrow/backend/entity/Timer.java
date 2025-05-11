package com.focusgrow.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "timer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "focus_duration", nullable = false)
    @Builder.Default
    private Integer focusDuration = 0;  // 기본값 0으로 설정, NULL 허용하지 않음

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TimerStatus status = TimerStatus.IN_PROGRESS;  // 기본값 IN_PROGRESS

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
