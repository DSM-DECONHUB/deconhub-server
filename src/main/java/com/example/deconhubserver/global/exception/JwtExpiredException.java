package com.example.deconhubserver.global.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class JwtExpiredException extends CustomException {
    public static final CustomException EXCEPTION =
            new JwtExpiredException();

    private JwtExpiredException() {
        super(ErrorCode.JWT_EXPIRED);
    }
}
