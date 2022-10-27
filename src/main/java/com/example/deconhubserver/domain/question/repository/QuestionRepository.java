package com.example.deconhubserver.domain.question.repository;

import com.example.deconhubserver.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
