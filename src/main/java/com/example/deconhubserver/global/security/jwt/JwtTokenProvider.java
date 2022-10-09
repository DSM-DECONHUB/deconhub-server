package com.example.deconhubserver.global.security.jwt;

import com.example.deconhubserver.domain.user.dto.UserResponse;
import com.example.deconhubserver.global.security.jwt.dao.RedisDao;
import com.example.deconhubserver.global.security.jwt.dto.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final RedisDao redisDao;

    @Value("${spring.jwt.key}")
    private String key;

    @Value("${spring.jwt.atk}")
    private final Long atkTime;

    @Value("${spring.jwt.rtk}")
    private final Long rtkTime;

    @PostConstruct
    protected void init(){key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponse createTokenByLogin(UserResponse userResponse){
        String atk = createToken(userResponse, atkTime, "atk");
        String rtk = createToken(userResponse, rtkTime, "rtk");
        redisDao.setValues(userResponse.getEmail(), rtk, Duration.ofMillis(rtkTime));
        return new TokenResponse(atk, rtk);
    }

    // Jwt 토큰 인증 정보조회
    public Authentication getAuthentication(String token){
        // Jwt 에서 정보 추출
        Claims claims = parseClaims(token);
        // 권한 정보가 없음
        if (claims.get("roles") == null){
            log.error("YOUR_NOT_ROLE");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰 복호화해서 가져오기
    public Claims parseClaims(String token){
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    // Request의 Header 에서 token값을 가져옴. "Authorization" : "TOKEN 값"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            log.error(e.toString());
            return false;
        }
    }

    public TokenResponse reissueAtk(UserResponse userResponse){
        String rtkInRedis = redisDao.getValues(userResponse.getEmail());
        if (Objects.isNull(rtkInRedis)) throw new IllegalStateException("인증 정보가 만료되었습니다.");
        return new TokenResponse(createToken(userResponse, atkTime, "atk"), null);
    }

    private String createToken(UserResponse userResponse, Long tokenTime, String type){
        Claims claims = Jwts.claims().setSubject(userResponse.getEmail());
        claims.put("roles", userResponse.getRole());
        Date date = new Date();
        return Jwts.builder()
                .claim("type", type)
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenTime))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }
}