package com.example.deconhubserver.domain.contest.dto;

import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class ContestList {

    private String period; // 대회기간

    private Long dateTime; // 신청 기간 결과

    private String title; // 대회 제목

    private String topic; // 대회 주제

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContestCategory category; // 대회 카테고리

    @Builder
    public ContestList(String title, String period, Long dateTime, String topic, ContestCategory category){
        this.title = title;
        this.period = period;
        this.dateTime = dateTime;
        this.topic = topic;
        this.category = category;
    }
}
