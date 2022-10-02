package com.example.deconhubserver.domain.user.dto;

import com.example.deconhubserver.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private final Long id;
    private final String email;
    private final String accountId;

    private UserResponse(Long id, String email, String accountId){
        this.id = id;
        this.email = email;
        this.accountId = accountId;
    }

    public static UserResponse of(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getAccountId());
    }
}
