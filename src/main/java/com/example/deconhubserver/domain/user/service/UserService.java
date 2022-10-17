package com.example.deconhubserver.domain.user.service;

import com.example.deconhubserver.domain.auth.exception.PasswordMissMatchedException;
import com.example.deconhubserver.domain.user.dto.LoginRequest;
import com.example.deconhubserver.domain.user.dto.SignupRequest;
import com.example.deconhubserver.domain.user.dto.UserResponse;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.exception.UserAlreadyExistsException;
import com.example.deconhubserver.domain.user.exception.UserNotFoundException;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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
}
