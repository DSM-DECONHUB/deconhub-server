package com.example.deconhubserver.domain.user.service;

import com.example.deconhubserver.domain.auth.exception.PasswordMissMatchedException;
import com.example.deconhubserver.domain.contest.dto.ContestList;
import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.user.dto.*;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.exception.CodeNotFoundException;
import com.example.deconhubserver.domain.user.exception.UserAlreadyExistsException;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import com.example.deconhubserver.infrastucture.mail.dto.MailRequest;
import com.example.deconhubserver.infrastucture.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    @Transactional
    public void signup(SignupRequest request) {

        //email은 중복이 되어도 상관없다고 생각하여 email 중복 Exception 생략함
        if (userRepository.existsUserByAccountId(request.getAccountId())) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        if (!request.getPassword().equals(request.getPasswordValid())) {
            throw PasswordMissMatchedException.EXCEPTION;
        }

        User user = new User(
                request.getAccountId(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Transactional
    public UserResponse login(LoginRequest request) {

        User user = userFacade.getUserByAccountId(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMissMatchedException.EXCEPTION;
        }

        user.setDeviceToken(request.getDeviceToken());

        return UserResponse.of(user);
    }

    // 이메일 코드 보낼때
    @Transactional
    public void lostPassword(MailRequest mailRequest) throws Exception {

        User user = userFacade.getUserByEmail(mailRequest.getEmail());

        mailService.mailSend(mailRequest, user.getAccountId());
    }

    // 코드 인증후 비밀번호 변경
    @Transactional
    public void setPassword(PasswordRequest request) {

        User user = userRepository.findUserByCode(request.getCode())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        if (user.getCode() != null) {

            if (!request.getNewPassword().equals(request.getNewPasswordValid())) {
                throw PasswordMissMatchedException.EXCEPTION;
            }

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            user.setCode(null);
            userRepository.save(user);
        }
    }

    @Transactional
    public void setPasswords(PasswordRequest request){
        User user = userFacade.getCurrentUser();

        if(!passwordEncoder.matches(request.getOriginalPassword(), user.getPassword())){
            throw new IllegalStateException("원래 비밀번호가 맞지 않습니다.");
        }

        if(!request.getNewPassword().equals(request.getNewPasswordValid())){
            throw new IllegalStateException("변경하는 비밀번호가 맞지 않습니다.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

    }

    // 자신의 정보 보기
    @Transactional(readOnly = true)
    public MyInfoResponse queryMyInfo() {
        User user = userFacade.getCurrentUser();

        return MyInfoResponse.builder()
                .accountId(user.getAccountId())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public void delUser() {
        User user = userFacade.getCurrentUser();
        userRepository.delete(user);
    }

}
