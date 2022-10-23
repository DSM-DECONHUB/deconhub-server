package com.example.deconhubserver.domain.contest.entity;

import com.example.deconhubserver.domain.contest.dto.ContestRequest;
import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import com.example.deconhubserver.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_contest")
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 대회 제목

    private String introduce; // 대회 한줄 소개

    private String period; // 대회기간

    private String place; // 대회 장소

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime signPeriod; // 신청기간

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createPeriod; // 게시글 만들어 진 시간

    private String sponsor; // 대회 후원사

    private String siteAddress; // 대회 사이트

    private String signCondition; //참가 조건

    private String signWay; // 참가 방법

    private String history; // 시상 내역

    private String topic; // 대회 주제

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContestCategory category; // 대회 카테고리

    @Builder
    public Contest(String title, String introduce, String period, String place, LocalDateTime signPeriod, String sponsor, String siteAddress, String signCondition, String signWay, String history, String topic, ContestCategory category, User user){
        this.title = title;
        this.introduce = introduce;
        this.period = period;
        this.place = place;
        this.createPeriod = LocalDateTime.now();
        this.signPeriod = signPeriod;
        this.sponsor = sponsor.replaceAll(",","");
        this.siteAddress = siteAddress;
        this.signCondition = signCondition;
        this.signWay = signWay;
        this.history = history;
        this.topic = topic;
        this.category = category;
        this.user = user;
    }

    public void setContest(ContestRequest request){
        if(request.getTitle() != null){
            this.title = request.getTitle();
        }
        if(request.getIntroduce() != null){
            this.introduce = request.getIntroduce();
        }
        if(request.getPeriod() != null){
            this.period = request.getPeriod();
        }
        if(request.getPeriod() != null){
            this.place = request.getPlace();
        }
        if(request.getSignPeriod() != null){
            this.signPeriod = request.getSignPeriod();
        }
        if(request.getSponsor() != null){
            this.sponsor = request.getSponsor().replaceAll(",","");
        }
        if(request.getSiteAddress() != null){
            this.siteAddress = request.getSiteAddress();
        }
        if(request.getSignCondition() != null){
            this.signCondition = request.getSignCondition();
        }
        if(request.getSignWay() != null){
            this.signWay = request.getSignWay();
        }
        if(request.getHistory() != null){
            this.history = request.getHistory();
        }
        if(request.getTopic() != null){
            this.topic = request.getTopic();
        }
        if(request.getCategory() != null){
            this.category = request.getCategory();
        }
    }
}
