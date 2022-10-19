package com.example.deconhubserver.domain.user.dto;

import com.example.deconhubserver.domain.user.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
    private String passwordValid;
    private String accountId;
    private Role role;
}
