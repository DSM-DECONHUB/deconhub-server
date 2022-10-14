package com.example.deconhubserver.global.config;

import com.example.deconhubserver.global.error.exception.ExceptionFilter;
import com.example.deconhubserver.global.filter.JwtAuthenticationFilter;
import com.example.deconhubserver.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthenticationFilter jwtTokenFilter = new JwtAuthenticationFilter(jwtTokenProvider);
        ExceptionFilter exceptionFilter = new ExceptionFilter();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionFilter, JwtAuthenticationFilter.class);
    }
}
