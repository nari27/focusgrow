package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.LevelResponse;

import java.util.List;

public interface LevelService {
    List<LevelResponse> getAllLevels();
    int calculateUserLevel(Integer userId);

}
