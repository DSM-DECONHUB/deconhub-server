package com.example.deconhubserver.domain.question.facade;

import com.example.deconhubserver.domain.question.entity.Question;
import com.example.deconhubserver.domain.question.exception.QuestionNotFoundException;
import com.example.deconhubserver.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class QuestionFacade {
    private final QuestionRepository questionRepository;

    public Question findById(Long id){
        return questionRepository.findById(id).orElseThrow(() -> QuestionNotFoundException.EXCEPTION);
    }

    public List<Question> findAllById(Sort sort){
        return questionRepository.findAll(sort);
    }
}
