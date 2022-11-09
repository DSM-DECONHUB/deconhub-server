package com.example.deconhubserver.domain.contest.repository;

import com.example.deconhubserver.domain.contest.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    @Query(value = "select c from Contest c where c.title LIKE %:kda%")
    List<Contest>findAllUserBySearch(String kda);
}
