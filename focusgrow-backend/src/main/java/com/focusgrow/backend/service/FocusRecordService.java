package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.FocusLogRequest;
import com.focusgrow.backend.dto.FocusLogResponse;

import java.time.LocalDate;
import java.util.List;

public interface FocusRecordService {
    FocusLogResponse addFocusRecord(FocusLogRequest focusLogRequest);
    List<FocusLogResponse> getFocusRecordsByDate(LocalDate date);
    int getTotalFocusTime(Integer userId); // ✅ 이 줄만 추가!
//    Object getFocusStats(String period);
}
