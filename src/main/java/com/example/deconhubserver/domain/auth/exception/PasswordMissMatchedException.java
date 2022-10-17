package com.example.deconhubserver.domain.auth.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class PasswordMissMatchedException extends CustomException {
    public static final CustomException EXCEPTION =
            new PasswordMissMatchedException();

    private PasswordMissMatchedException() {
        super(ErrorCode.PASSWORD_MISS_MATCHED);
    }
}
