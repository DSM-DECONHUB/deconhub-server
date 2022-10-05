package com.example.deconhubserver.domain.user.service;

import com.example.deconhubserver.domain.user.dto.LoginRequest;
import com.example.deconhubserver.domain.user.dto.SignupRequest;
import com.example.deconhubserver.domain.user.dto.UserResponse;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signup(SignupRequest request){
        boolean isExist = userRepository.existsByEmail(request.getEmail());
        if(isExist) throw new IllegalStateException("이미 존재하는 이메일 입니다.");

        User user = new User(
                request.getAccountId(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);
        return UserResponse.of(user);
    }

    @Transactional
    public UserResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 맞지 않습니다."));

        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return UserResponse.of(user);
        }else throw new IllegalStateException("비밀번호가 맞지 않습니다.");
    }
}
