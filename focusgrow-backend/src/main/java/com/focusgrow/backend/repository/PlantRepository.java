package com.focusgrow.backend.repository;

import com.focusgrow.backend.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
// 사용자 ID로 식물 찾기
    Optional<Plant> findByUserId(Integer userId);
}
