package com.example.deconhubserver.domain.question.controller;

import com.example.deconhubserver.domain.question.dto.QuestionList;
import com.example.deconhubserver.domain.question.dto.QuestionRequest;
import com.example.deconhubserver.domain.question.dto.QuestionResponse;
import com.example.deconhubserver.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/create/{contest-id}")
    public void questionCreate(@RequestBody QuestionRequest request, @PathVariable(name = "contest-id") Long id){
        questionService.createQuestion(request, id);
    }

    @GetMapping("/list/{contest-id}")
    public List<QuestionList> questionList(@PathVariable(name = "contest-id") Long id){
        return questionService.questionList(id);
    }

    @GetMapping("/detail/{question-id}")
    public QuestionResponse questionDetail(@PathVariable(name = "question-id") Long id){
        return questionService.questionDetail(id);
    }

}
