package com.example.deconhubserver.global.mail.service;

import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import com.example.deconhubserver.global.mail.dto.MailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    private String randomMessage(String accountId) {

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("이메일을 찾을 수 없습니다."));

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }

        user.setCode(sb.toString());
        userRepository.save(user);
        return sb.toString();
    }

    public void mailSend(MailRequest mailRequest, String accountId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getEmail()); //받는 사람의 주소
        message.setSubject("'Deconhub' 인증 코드"); // 제목
        message.setText(accountId + "님 오늘도 즐거운 하루였나요??\n" + "인증번호는 ' " + randomMessage(accountId) + " ' 입니다.\n내일도 만나요!!"); // 내용
        javaMailSender.send(message); //메일 발송
    }
}
