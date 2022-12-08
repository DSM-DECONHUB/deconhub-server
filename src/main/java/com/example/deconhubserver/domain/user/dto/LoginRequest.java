package com.example.deconhubserver.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {
    private String accountId;
    private String password;
    private String deviceToken;
}
