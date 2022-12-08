package com.example.deconhubserver.infrastucture.fcm;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.notification.entity.NotificationEntity;
import com.example.deconhubserver.domain.notification.repository.NotificationRepository;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import com.example.deconhubserver.infrastucture.fcm.dto.NotificationRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class FcmNotification implements FcmUtil {
    private final UserFacade userFacade;
    private final NotificationRepository notificationRepository;

    @Value("${firebase.path}")
    private String path;

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    @PostConstruct
    @Override
    public void initialize() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream())
                            .createScoped(Arrays.asList(SCOPES)))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.error("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        NotificationEntity notificationEntity = notificationRepository.save(
                NotificationEntity.builder()
                        .title(notificationRequest.getTitle())
                        .content(notificationRequest.getContent())
                        .data(notificationRequest.getData())
                        .user(notificationRequest.getUser())
                        .build());

        Message message = Message.builder()
                .putData("notification-id", notificationEntity.getId().toString())
                .putData("click_action", notificationRequest.getClickAction())
                .setToken(notificationRequest.getUser().getDeviceToken())
                .setNotification(
                        Notification.builder()
                                .setTitle(notificationRequest.getTitle())
                                .setBody(notificationEntity.getContent())
                                .build())
                .setAndroidConfig(AndroidConfig.builder()
                        .setDirectBootOk(true)
                        .setTtl(TimeUnit.SECONDS.toMillis(30))
                        .build())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    @Override
    public void sendContest(Contest contest) {
        User user = userFacade.getCurrentUser();
        String title = "DECONHUB";
        String content = "["+contest.getTitle()+"]" + " 대회가 생성되었습니다!";
        sendNotification(
                NotificationRequest.builder()
                        .user(user)
                        .title(title)
                        .content(content)
                        .data(contest.getId().toString())
                        .build()
        );
    }

}
