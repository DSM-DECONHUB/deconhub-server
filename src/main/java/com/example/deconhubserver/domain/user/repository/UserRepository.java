package com.example.deconhubserver.domain.user.repository;

import com.example.deconhubserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByCode(String code);

    Optional<User> findUserByAccountId(String accountId);

    Optional<User> findUserByEmail(String email);

    boolean existsUserByAccountId(String accountId);
}
