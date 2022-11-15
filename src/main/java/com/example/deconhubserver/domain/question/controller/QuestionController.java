package com.example.deconhubserver.domain.question.controller;

import com.example.deconhubserver.domain.question.dto.*;
import com.example.deconhubserver.domain.question.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "question", description = "Q&A 관련 API 입니다.")
@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "Q&A 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/{contest-id}")
    public void questionCreate(@RequestBody QuestionTitleRequest request, @PathVariable(name = "contest-id") Long id){
        questionService.createQuestion(request, id);
    }

    @Operation(summary = "Q&A 리스트 보기")
    @GetMapping("/list/{contest-id}")
    public List<QuestionList> questionList(@PathVariable(name = "contest-id") Long id){
        return questionService.questionList(id);
    }

    @Operation(summary = "Q&A 상세 보기")
    @GetMapping("/detail/{question-id}")
    public QuestionResponse questionDetail(@PathVariable(name = "question-id") Long id){
        return questionService.questionDetail(id);
    }

    @Operation(summary = "Q&A 리스트 보기")
    @GetMapping("/list")
    public QuestionContestList questionDetail(){
        return questionService.questionContestResponseList();
    }

    @PatchMapping("/modify/{question-id}")
    public void questionModify(@PathVariable(name = "question-id") Long id, @RequestBody QuestionTitleRequest request){
        questionService.modifyQuestion(id, request);
    }

    @PatchMapping("/answer/{question-id}")
    public void answerQuestion(@PathVariable(name = "question-id") Long id, @RequestBody QuestionContentRequest request){
        questionService.questionAnswer(id, request);
    }

}
