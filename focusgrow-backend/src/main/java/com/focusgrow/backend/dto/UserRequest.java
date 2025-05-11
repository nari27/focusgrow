package com.focusgrow.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Integer id;
    private String username;
    private String email;
    private String password;
}