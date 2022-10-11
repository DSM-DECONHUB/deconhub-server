package com.example.deconhubserver.domain.user.service;

import com.example.deconhubserver.domain.user.dto.LoginRequest;
import com.example.deconhubserver.domain.user.dto.SignupRequest;
import com.example.deconhubserver.domain.user.dto.UserResponse;
import com.example.deconhubserver.domain.user.entity.User;
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
        boolean isExist = userRepository.existsByEmail(request.getEmail());
        boolean isExist2 = userRepository.existsByAccountId(request.getAccountId());
        if (isExist) throw new IllegalStateException("이미 가입하신 이메일 입니다.");
        if (isExist2) throw new IllegalStateException("이미 있는 아이디 입니다.");

        User user = new User(
                request.getAccountId(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Transactional
    public UserResponse login(LoginRequest request) {

        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 맞지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 맞지 않습니다.");
        }

        return UserResponse.of(user);
    }
}
