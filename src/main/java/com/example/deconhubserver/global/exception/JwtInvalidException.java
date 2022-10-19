package com.example.deconhubserver.global.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class JwtInvalidException extends CustomException {
    public static final CustomException EXCEPTION =
            new JwtInvalidException();

    private JwtInvalidException() {
        super(ErrorCode.JWT_INVALID);
    }
}
