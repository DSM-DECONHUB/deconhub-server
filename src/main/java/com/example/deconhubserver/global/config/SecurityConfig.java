package com.example.deconhubserver.global.config;

import com.example.deconhubserver.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .csrf().disable()
                .cors().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()

                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .antMatchers(HttpMethod.POST,"/user/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/user/reissue").authenticated()
                .antMatchers(HttpMethod.POST,"/user/lost/password").permitAll()
                .antMatchers(HttpMethod.PATCH,"/user/lost/password").permitAll()
                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .antMatchers(HttpMethod.GET,"/contest/list").permitAll()
                .antMatchers(HttpMethod.GET,"/qna/list").permitAll()
                .antMatchers(HttpMethod.POST, "/contest/create").permitAll()
                .antMatchers(HttpMethod.POST,"/qna/create/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider));

        return http.build();
    }
}
