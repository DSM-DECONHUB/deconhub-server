package com.example.deconhubserver.infrastucture.fcm.dto;

import com.example.deconhubserver.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationRequest {
    private final User user;
    private final String title;
    private final String content;
    private final String data;
    private final String clickAction;
}
