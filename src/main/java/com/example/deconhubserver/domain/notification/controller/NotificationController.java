package com.example.deconhubserver.domain.notification.controller;

import com.example.deconhubserver.domain.notification.dto.NotificationListResponse;
import com.example.deconhubserver.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/{notification-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void watchedNotification(@PathVariable(name = "notification-id") Long notificationId) {
        notificationService.WatchedNotification(notificationId);
    }

    @GetMapping
    public NotificationListResponse queryNotificationList() {
        return notificationService.queryNotification();
    }
}
