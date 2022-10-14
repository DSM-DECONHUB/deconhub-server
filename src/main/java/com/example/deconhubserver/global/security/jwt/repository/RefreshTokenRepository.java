package com.example.deconhubserver.global.security.jwt.repository;

import com.example.deconhubserver.global.security.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;


public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    boolean existsByAccountId(String accountId);
}
