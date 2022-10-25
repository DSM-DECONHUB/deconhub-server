package com.example.deconhubserver.domain.contest.dto;

import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContestList {

    private String period; // 대회기간

    private Long dateTime; // 신청 기간 결과

    private String title; // 대회 제목

    private String topic; // 대회 주제

    private ContestCategory category; // 대회 카테고리


}
