package com.example.deconhubserver.domain.user.entity;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.question.entity.Question;
import com.example.deconhubserver.domain.user.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 8, max = 20)
    private String accountId;

    @Email
    private String email;

    @NotNull
    @Length(max = 68)
    private String password;

    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "device_token")
    private String deviceToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Contest> contests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @Builder
    public User(String accountId, String email, String password) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCode(String code){ this.code = code;}

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

}
