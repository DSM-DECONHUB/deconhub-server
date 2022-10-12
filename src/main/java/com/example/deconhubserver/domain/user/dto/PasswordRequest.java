package com.example.deconhubserver.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordRequest {
    private String code;
    private String originalPassword;
    private String newPassword;
    private String newPasswordValid;
}
