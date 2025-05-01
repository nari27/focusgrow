package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.*;

public interface UserService {
    UserResponse register(UserRequest request);
    UserResponse login(UserRequest request);
    UserResponse getMyInfo(Integer userId);
    int getTotalFocusTime(Integer userId);
}
