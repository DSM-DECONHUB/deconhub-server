package com.example.deconhubserver.global.mail.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@RequiredArgsConstructor
public class MailRequest {
    @Email
    private String email;
}
