package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.*;
import com.focusgrow.backend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public UserResponse register(@RequestBody UserRequest request) {
        return userServiceImpl.register(request);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request) {
        return userServiceImpl.login(request);
    }

    @GetMapping("/me")
    public UserResponse getMyInfo(@RequestParam Integer userId) {
        return userServiceImpl.getMyInfo(userId);
    }

    @GetMapping("/{id}/focus-time")
    public int getTotalFocusTime(@PathVariable Integer id) {
        return userServiceImpl.getTotalFocusTime(id);
    }
}
