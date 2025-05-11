package com.focusgrow.backend.repository;

import com.focusgrow.backend.entity.FocusRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FocusRecordRepository extends JpaRepository<FocusRecord, Integer> {
    List<FocusRecord> findByDate(LocalDate date);

//    // 주간/월간 통계는 JPQL 쿼리로 작성 가능 (예시로 통계 쿼리 추가)
//    // 예시: SELECT SUM(f.focusTime) FROM FocusRecord f WHERE f.date BETWEEN :startDate AND :endDate
//    List<Object> getWeeklyStats();
//    List<Object> getMonthlyStats();
}
