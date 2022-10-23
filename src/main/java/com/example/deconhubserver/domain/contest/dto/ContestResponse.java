package com.example.deconhubserver.domain.contest.dto;

import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
public class ContestResponse {

    private final String title; // 대회 제목

    private final String introduce; // 대회 한줄 소개

    private final String period; // 대회기간

    private final String place; // 대회 장소

    private final Long dateTime; // 신청기간 결과

    private final String sponsor; // 대회 후원사

    private final String siteAddress; // 대회 사이트

    private final String signCondition; //참가 조건

    private final String signWay; // 참가 방법

    private final String history; // 시상 내역

    private final String topic; // 대회 주제

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final ContestCategory category; // 대회 카테고리

}
