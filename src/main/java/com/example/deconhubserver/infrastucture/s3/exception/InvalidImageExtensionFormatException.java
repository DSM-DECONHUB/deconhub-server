package com.example.deconhubserver.infrastucture.s3.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class InvalidImageExtensionFormatException extends CustomException {
    public static final CustomException EXCEPTION =
            new InvalidImageExtensionFormatException();

    private InvalidImageExtensionFormatException() {
        super(ErrorCode.INVALID_IMAGE_EXTENSION);
    }
}
