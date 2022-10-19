package com.example.deconhubserver.global.mail.service;

import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.exception.EmailNotFoundException;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import com.example.deconhubserver.global.mail.dto.MailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    private String randomMessage(String accountId) {

        SecureRandom random = new SecureRandom();
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> EmailNotFoundException.EXCEPTION);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }

        user.setCode(sb.toString());
        userRepository.save(user);
        return sb.toString();
    }

    public void mailSend(MailRequest mailRequest, String accountId) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, mailRequest.getEmail());
        message.setSubject("'Deconhub' 인증 코드");

        String msgg = "";
        msgg += "<h1>안녕하세요 " + accountId + "님 비밀번호 인증 코드 입니다.</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg += "<br>";
        msgg += "CODE : <strong>";
        msgg += randomMessage(accountId) + "</strong>";

        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("hoyoung7827@gmail.com", "Deconhub"));

        javaMailSender.send(message);

    }
}
