package com.example.deconhubserver.global.error.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
