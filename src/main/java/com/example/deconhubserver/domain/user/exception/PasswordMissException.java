package com.example.deconhubserver.domain.user.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class PasswordMissException extends CustomException {
    public static final PasswordMissException EXCEPTION =
            new PasswordMissException();

    private PasswordMissException(){
        super(ErrorCode.PASSWORD_MISS_MATCHED);
    }
}
