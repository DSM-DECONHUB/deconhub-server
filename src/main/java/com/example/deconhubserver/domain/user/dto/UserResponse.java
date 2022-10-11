package com.example.deconhubserver.domain.user.dto;

import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String accountId;
    private Role role;

    public static UserResponse of(User user){
        return UserResponse.builder()
                .accountId(user.getAccountId())
                .role(user.getRole()).build();
    }
}
