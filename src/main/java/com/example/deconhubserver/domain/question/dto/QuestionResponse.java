package com.example.deconhubserver.domain.question.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {

    private String title;

    private String content;

    private String accountId;
}
