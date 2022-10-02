package com.example.deconhubserver.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Subject {

    private final Long id;
    private final String email;
    private final String accountId;
    private final String type;

    public static Subject atk(Long id, String email, String accountId){
        return new Subject(id, email, accountId, "AK");
    }

    public static Subject rtk(Long id, String email, String accountId){
        return new Subject(id, email, accountId, "RK");
    }

}
