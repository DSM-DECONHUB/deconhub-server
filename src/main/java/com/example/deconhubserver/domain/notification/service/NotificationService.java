package com.example.deconhubserver.domain.notification.service;

import com.example.deconhubserver.domain.notification.dto.NotificationListResponse;
import com.example.deconhubserver.domain.notification.dto.NotificationListResponse.NotificationResponse;
import com.example.deconhubserver.domain.notification.entity.NotificationEntity;
import com.example.deconhubserver.domain.notification.exception.NotificationNotFoundException;
import com.example.deconhubserver.domain.notification.repository.NotificationRepository;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final UserFacade userFacade;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void WatchedNotification(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        notification.updateWatchTure();
    }

    @Transactional(readOnly = true)
    public NotificationListResponse queryNotification() {

        User user = userFacade.getCurrentUser();

        List<NotificationResponse> notificationResponseList = notificationRepository.findByUser(user)
                .stream()
                .map(notificationEntity -> NotificationResponse.builder()
                        .id(notificationEntity.getId())
                        .title(notificationEntity.getTitle())
                        .content(notificationEntity.getContent())
                        .isWatch(notificationEntity.getIsWatch())
                        .data(notificationEntity.getData())
                        .build())
                .collect(Collectors.toList());

        return NotificationListResponse.builder()
                .notificationResponseList(notificationResponseList)
                .build();
    }
}
