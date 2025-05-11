package com.focusgrow.backend.controller;

import com.focusgrow.backend.dto.UserRequest;
import com.focusgrow.backend.dto.UserResponse;
import com.focusgrow.backend.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    // 회원가입
    @PostMapping
    public UserResponse register(@RequestBody UserRequest request) {
        return userServiceImpl.register(request);
    }

    // 로그인 (세션에 id 저장)
    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request, HttpSession session) {
        UserResponse userResponse = userServiceImpl.login(request);
        session.setAttribute("id", userResponse.getId()); // 세션에 사용자 ID 저장
        return userResponse;
    }


    // 내 정보 조회 (세션에서 id를 가져옴)
    @GetMapping("/me")
    public UserResponse getMyInfo(HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");
        if (id == null) {
            throw new RuntimeException("User not logged in");
        }
        return userServiceImpl.getMyInfo(id); // id를 기반으로 내 정보 조회
    }

    // 총 집중 시간 조회
    @GetMapping("/{id}/focus-time")
    public int getTotalFocusTime(@PathVariable Integer id) {
        return userServiceImpl.getTotalFocusTime(id);
    }
}
