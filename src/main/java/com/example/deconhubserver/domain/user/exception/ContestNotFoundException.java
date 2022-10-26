package com.example.deconhubserver.domain.user.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class ContestNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
        new ContestNotFoundException();


    private ContestNotFoundException() {
        super(ErrorCode.CONTEST_NOT_FOUND);
    }
}
