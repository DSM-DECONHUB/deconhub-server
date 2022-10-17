package com.example.deconhubserver.global.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private final int status;
    private final String message;

    @Override
    public String toString() {
        return "{\n\t\"status\": $status,\n\t\"message\": \"$message\"\n}";
    }
}
