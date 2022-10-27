package com.example.deconhubserver.domain.question.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class QuestionNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new QuestionNotFoundException();

    public QuestionNotFoundException() {
        super(ErrorCode.QUESTION_NOT_FOUND);
    }
}
