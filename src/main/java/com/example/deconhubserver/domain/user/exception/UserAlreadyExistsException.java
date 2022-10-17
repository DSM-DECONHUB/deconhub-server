package com.example.deconhubserver.domain.user.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;
import com.mysql.cj.x.protobuf.Mysqlx;

public class UserAlreadyExistsException extends CustomException {
    public static final CustomException EXCEPTION =
            new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
