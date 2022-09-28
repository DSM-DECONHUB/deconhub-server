package com.example.deconhubserver.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickName", nullable = false)
    private String name;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public void setPassword(String password){
        this.password = password;
    }

}
