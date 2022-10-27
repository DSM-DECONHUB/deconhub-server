package com.example.deconhubserver.domain.question.controller;

import com.example.deconhubserver.domain.question.dto.QuestionList;
import com.example.deconhubserver.domain.question.dto.QuestionRequest;
import com.example.deconhubserver.domain.question.dto.QuestionResponse;
import com.example.deconhubserver.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/create")
    public void questionCreate(@RequestBody QuestionRequest request){
        questionService.createQuestion(request);
    }

    @GetMapping("/list")
    public List<QuestionList> questionList(){
        return questionService.questionList();
    }

    @GetMapping("/detail/{question-id}")
    public QuestionResponse questionDetail(@PathVariable(name = "question-id") Long id){
        return questionService.questionDetail(id);
    }

}
