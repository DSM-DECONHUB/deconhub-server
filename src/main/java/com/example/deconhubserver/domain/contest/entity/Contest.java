package com.example.deconhubserver.domain.contest.entity;

import com.example.deconhubserver.domain.contest.dto.ContestRequest;
import com.example.deconhubserver.domain.question.entity.Question;
import com.example.deconhubserver.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    private String sponsor; // 대회 후원사

    private String siteAddress; // 대회 링크

    private String signCondition; //참가 조건

    private String signWay; // 참가 방법

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "contest", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    private String category; // 대회 카테고리

    @Builder
    public Contest(String title, String introduce, String period, String place, LocalDateTime signPeriod, String sponsor, String siteAddress, String signCondition, String signWay, String category, User user){
        this.title = title;
        this.introduce = introduce;
        this.period = period;
        this.place = place;
        this.signPeriod = signPeriod;
        this.sponsor = sponsor.replace(",","");
        this.siteAddress = siteAddress;
        this.signCondition = signCondition;
        this.signWay = signWay;
        this.category = category;
        this.user = user;
    }

    public void setContest(ContestRequest request){
            this.title = request.getTitle();
            this.introduce = request.getIntroduce();
            this.period = request.getPeriod();
            this.place = request.getPlace();
            this.signPeriod = request.getSignPeriod();
            this.sponsor = request.getSponsor().replace(",","");
            this.siteAddress = request.getSiteAddress();
            this.signCondition = request.getSignCondition();
            this.signWay = request.getSignWay();
            this.category = request.getCategory();
    }
}
