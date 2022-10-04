package com.example.deconhubserver.domain.user.dto;

import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.enums.Role;
import lombok.Getter;

@Getter
public class UserResponse {
    private final String email;
    private final String accountId;
    private final Role role;

    private UserResponse(String email, String accountId, Role role){
        this.email = email;
        this.accountId = accountId;
        this.role = role;
    }

    public static UserResponse of(User user){
        return new UserResponse(
                user.getEmail(),
                user.getAccountId(),
                user.getRole());
    }
}
