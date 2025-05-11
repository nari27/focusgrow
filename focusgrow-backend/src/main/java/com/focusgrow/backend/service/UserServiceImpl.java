package com.focusgrow.backend.service;

import com.focusgrow.backend.dto.*;
import com.focusgrow.backend.entity.User;
import com.focusgrow.backend.repository.UserRepository;
import com.focusgrow.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse register(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // 실제로는 암호화 필요
                .build();
        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    @Override
    public UserResponse login(UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 실제로는 비밀번호 검증 필요
        return toResponse(user);
    }

    @Override
    public UserResponse getMyInfo(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toResponse(user);
    }

    @Override
    public int getTotalFocusTime(Integer id) {
        // 실제 구현은 FocusRecord 테이블을 조회해서 총합 구해야 함
        return 0;
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
