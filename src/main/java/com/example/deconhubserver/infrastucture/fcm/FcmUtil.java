package com.example.deconhubserver.infrastucture.fcm;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.infrastucture.fcm.dto.NotificationRequest;

public interface FcmUtil {

    void initialize();

    void sendNotification(NotificationRequest notificationRequest);

    void sendContest(Contest contest);
}
