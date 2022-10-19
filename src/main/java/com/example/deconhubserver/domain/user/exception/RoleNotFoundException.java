package com.example.deconhubserver.domain.user.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class RoleNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new RoleNotFoundException();

    private RoleNotFoundException(){
        super(ErrorCode.ROLE_NOT_FOUND);
    }
}
