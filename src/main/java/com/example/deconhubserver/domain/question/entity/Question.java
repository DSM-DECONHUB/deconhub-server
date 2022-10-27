package com.example.deconhubserver.domain.question.entity;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contest_id",nullable = false)
    private Contest contest;

    private String title;

    private String content;

    public void setQuestion(String title){
        this.title = title;
    }

    public void answerQuestion(String content){
        this.content = content;
    }

    @Builder
    public Question(Contest contest,User user, String title){
        this.user = user;
        this.title = title;
        this.contest = contest;
    }

}
