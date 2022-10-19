package com.example.deconhubserver.domain.user.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class EmailNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new EmailNotFoundException();

    private EmailNotFoundException() {
        super(ErrorCode.EMAIL_NOT_FOUND);
    }
}
