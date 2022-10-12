package com.example.deconhubserver.domain.user.repository;

import com.example.deconhubserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCode(String code);

    Optional<User> findByAccountId(String accountId);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByAccountId(String accountId);
}
