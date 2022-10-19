package com.example.deconhubserver.domain.contest.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContestCategory {

    HACKATHON("hackathon", "해커톤"),
    OFFLINE("offline","오프라인"),
    ONLINE("online", "온라인"),
    PROCEEDING("proceeding", "진행중"),
    ENDED("ended", "종료됨"),
    CANAPPLY("canApply", "신청가능"),
    STUDENTCONTEST("studentContest", "학생대회"),
    SCHOOLCONTEST("schoolContest", "학교대회");

    private final String category;
    private final String title;
}
