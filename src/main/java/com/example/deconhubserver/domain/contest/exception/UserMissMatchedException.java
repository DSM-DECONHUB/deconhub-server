package com.example.deconhubserver.domain.contest.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class UserMissMatchedException extends CustomException {
    public static final CustomException EXCEPTION =
            new UserMissMatchedException();


    private UserMissMatchedException() {
        super(ErrorCode.USER_MISS_MATCHED);
    }
}
