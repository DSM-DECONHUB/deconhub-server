package com.example.deconhubserver.domain.user.controller;

import com.example.deconhubserver.domain.contest.dto.ContestList;
import com.example.deconhubserver.domain.user.dto.*;
import com.example.deconhubserver.domain.user.service.UserService;
import com.example.deconhubserver.infrastucture.mail.dto.MailRequest;
import com.example.deconhubserver.global.security.auth.AuthDetails;
import com.example.deconhubserver.global.security.jwt.JwtTokenProvider;
import com.example.deconhubserver.global.security.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "user", description = "유저 관련 API 입니다.")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void login(@RequestBody SignupRequest request){
        userService.signup(request);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody LoginRequest request){
        UserResponse userResponse = userService.login(request);
        return jwtTokenProvider.createTokenByLogin(userResponse);
    }

    @Operation(summary = "토큰 재발급")
    @GetMapping("/reissue")
    public TokenResponse reissue(
            @AuthenticationPrincipal AuthDetails authDetails
    ){
        UserResponse userResponse = UserResponse.of(authDetails.getUser());
        return jwtTokenProvider.reissueAtk(userResponse);
    }

    @Operation(summary = "인증 코드 보낼 이메일 입력")
    @PostMapping("/lost/password")
    public void mail(@Valid @RequestBody MailRequest request) throws Exception {
        userService.lostPassword(request);
    }

    @Operation(summary = "인증 코드 입력후 비번 변경")
    @PatchMapping("/lost/password")
    public void setPassword(@Valid @RequestBody PasswordRequest request) {
        userService.setPassword(request);
    }

    @Operation(summary = "자신의 정보 보기")
    @GetMapping()
    public MyInfoResponse queryMyInfo() {
        return userService.queryMyInfo();
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/delete")
    public void delUser(){
        userService.delUser();
    }

    @Operation(summary = "자신의 참가한 대회 보기")
    @GetMapping("/my/contest")
    public List<ContestList> myContestList(){
        return userService.attendContest();
    }

}
