package com.example.deconhubserver.domain.contest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContestRequest {

    private String title; // 대회 제목

    private String introduce; // 대회 한줄 소개

    private String period; // 대회기간

    private String place; // 대회 장소

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime signPeriod; // 신청기간

    private String sponsor; // 대회 후원사

    private String siteAddress; // 대회 링크

    private String signCondition; //참가 조건

    private String signWay; // 참가 방법

    private String category; // 대회 카테고리
}
