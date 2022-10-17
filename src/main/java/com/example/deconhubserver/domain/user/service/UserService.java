package com.example.deconhubserver.domain.user.service;

import com.example.deconhubserver.domain.auth.exception.PasswordMissMatchedException;
import com.example.deconhubserver.domain.user.dto.LoginRequest;
import com.example.deconhubserver.domain.user.dto.PasswordRequest;
import com.example.deconhubserver.domain.user.dto.SignupRequest;
import com.example.deconhubserver.domain.user.dto.UserResponse;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.exception.UserAlreadyExistsException;
import com.example.deconhubserver.domain.user.exception.UserNotFoundException;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import com.example.deconhubserver.global.mail.dto.MailRequest;
import com.example.deconhubserver.global.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request) {

        //email은 중복이 되어도 상관없다고 생각하여 email 중복 Exception 생략함
        if (!userRepository.existsByAccountId(request.getAccountId())) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        User user = new User(
                request.getAccountId(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Transactional
    public UserResponse login(LoginRequest request) {

        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMissMatchedException.EXCEPTION;
        }

        return UserResponse.of(user);
    }

    // 이메일 코드 보낼때
    @Transactional
    public void lostPassword(MailRequest mailRequest)throws Exception {

        User user = userRepository.findByEmail(mailRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일을 찾을 수 없습니다."));

        mailService.mailSend(mailRequest, user.getAccountId());

    }

    // 코드 인증후 비밀번호 변경
    @Transactional
    public void setPassword(PasswordRequest request) {

        User user = userRepository.findByCode(request.getCode())
                .orElseThrow(() -> new IllegalArgumentException("코드를 다시 입력 해주세요.."));

        if (user.getCode() != null) {

            if (!request.getNewPassword().equals(request.getNewPasswordValid())) {
                throw new IllegalStateException("비밀번호가 맞지 않습니다.");
            }

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setCode(null);
            userRepository.save(user);
        }

    }
}
