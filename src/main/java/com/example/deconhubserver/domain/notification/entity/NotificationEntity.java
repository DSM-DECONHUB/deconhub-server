package com.example.deconhubserver.domain.notification.entity;

import com.example.deconhubserver.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_notification")
@Entity
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 50)
    private String title;

    @NotNull
    @Length(max = 300)
    private String content;

    @ColumnDefault("false")
    private Boolean isWatch;

    @NotNull
    private String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public NotificationEntity(String title, String content, String data, User user) {
        this.title = title;
        this.content = content;
        this.data = data;
        this.user = user;
    }

    public void updateWatchTure() {
        this.isWatch = true;
    }
}
