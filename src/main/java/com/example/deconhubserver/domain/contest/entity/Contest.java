package com.example.deconhubserver.domain.contest.entity;

import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String introduce; // 대회 한줄 소개

    private String period; // 대회기간

    private String place; // 대회 장소

    private LocalDateTime signPeriod; // 신청기간

    private String sponsor; // 대회 후원사

    private String siteAddress; // 대회 사이트

    private String condition; //참가 조건

    private String signWay; // 참가 방법

    private String history; // 시상 내역

    private String topic; // 대회 주제

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContestCategory category; // 대회 카테고리
}
