package com.example.deconhubserver.domain.user.dto;

import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private final String email;
    private final String accountId;
    private final Role role;

    public static UserResponse of(User user){
        return UserResponse.builder()
                .email(user.getEmail())
                .accountId(user.getAccountId())
                .role(user.getRole()).build();
    }
}
