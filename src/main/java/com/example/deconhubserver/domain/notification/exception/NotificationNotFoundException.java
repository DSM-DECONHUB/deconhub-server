package com.example.deconhubserver.domain.notification.exception;

import com.example.deconhubserver.global.error.ErrorCode;
import com.example.deconhubserver.global.error.exception.CustomException;

public class NotificationNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new NotificationNotFoundException();

    private NotificationNotFoundException() {
        super(ErrorCode.NOTIFICATION_NOT_FOUND);
    }
}
