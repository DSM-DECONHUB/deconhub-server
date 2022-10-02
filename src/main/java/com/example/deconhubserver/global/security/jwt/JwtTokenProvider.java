package com.example.deconhubserver.global.security.jwt;

import com.example.deconhubserver.domain.user.dto.UserResponse;
import com.example.deconhubserver.global.security.jwt.dto.Subject;
import com.example.deconhubserver.global.security.jwt.dto.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    private final ObjectMapper objectMapper;

    @Value("${spring.jwt.key}")
    private String key;

    private final Long atkTime = 60 * 60 * 1000L;

    @PostConstruct
    protected void init(){key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponse createTokenByLogin(UserResponse userResponse) throws JsonProcessingException{
        Subject atkSubject = Subject.atk(
                userResponse.getId(),
                userResponse.getEmail(),
                userResponse.getAccountId());
        String atk = createToken(atkSubject, atkTime);
        return new TokenResponse(atk, null);
    }

    public Subject getSubject(String atk) throws JsonProcessingException{
        String subjectStr = Jwts.parser().setSigningKey(key).parseClaimsJws(atk).getBody().getSubject();
        return objectMapper.readValue(subjectStr, Subject.class);
    }

    private String createToken(Subject subject, Long tokenTime) throws JsonProcessingException{
        String subjectStr = objectMapper.writeValueAsString(subject);
        log.info("subjectStr = {} ", subjectStr);
        Claims claims = Jwts.claims().setSubject(subjectStr);
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenTime))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }
}
