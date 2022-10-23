package com.example.deconhubserver.domain.contest.repository;

import com.example.deconhubserver.domain.contest.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {
}
