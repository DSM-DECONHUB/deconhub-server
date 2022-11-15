package com.example.deconhubserver.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionContestList {
    private final List<QuestionContestResponse> list;
}
