package com.example.deconhubserver.domain.user.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class CodeNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new CodeNotFoundException();

    private CodeNotFoundException() {
        super(ErrorCode.CODE_NOT_FOUND);
    }
}
