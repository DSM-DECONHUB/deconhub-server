package com.example.deconhubserver.domain.notification.repository;

import com.example.deconhubserver.domain.notification.entity.NotificationEntity;
import com.example.deconhubserver.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotificationRepository extends CrudRepository<NotificationEntity, Long> {
    Optional<NotificationEntity> findByUser(User user);
}
