package com.example.deconhubserver.domain.question.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionContestResponse {
    private final Long contestId;
    private final Long questionId;
    private final String contestName;
    private final String questionTitle;
}
