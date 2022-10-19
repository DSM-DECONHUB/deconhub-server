package com.example.deconhubserver.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyInfoResponse {

    private final String accountId;
    private final String email;
}
