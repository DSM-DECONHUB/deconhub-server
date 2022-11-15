package com.example.deconhubserver.domain.question.service;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.contest.exception.UserMissMatchedException;
import com.example.deconhubserver.domain.contest.facade.ContestFacade;
import com.example.deconhubserver.domain.question.dto.*;
import com.example.deconhubserver.domain.question.entity.Question;
import com.example.deconhubserver.domain.question.facade.QuestionFacade;
import com.example.deconhubserver.domain.question.repository.QuestionRepository;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.enums.Role;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionFacade questionFacade;
    private final QuestionRepository questionRepository;
    private final UserFacade userFacade;
    private final ContestFacade contestFacade;

    @Transactional
    public void createQuestion(QuestionTitleRequest request, Long id) {
        User user = userFacade.getCurrentUser();
        Contest contest = contestFacade.findById(id);
        Question question = new Question(
                contest,
                user,
                request.getTitle()
        );
        questionRepository.save(question);
    }

    @Transactional(readOnly = true)
    public List<QuestionList> questionList(Long id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Question> questions = questionFacade.findAllById(sort);
        List<QuestionList> questionLists = new ArrayList<>();

        for (Question question : questions) {
            if (question.getContest().getId().equals(id)) {
                QuestionList dto = QuestionList.builder()
                        .title(question.getTitle()).build();
                questionLists.add(dto);
            }
        }
        return questionLists;
    }

    @Transactional(readOnly = true)
    public QuestionResponse questionDetail(Long id) {
        Question question = questionFacade.findById(id);
        return QuestionResponse.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .accountId(question.getUser().getAccountId()).build();
    }

    @Transactional(readOnly = true)
    public QuestionContestList questionContestResponseList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Question> questions = questionFacade.findAllById(sort);
        List<QuestionContestResponse> questionLists = new ArrayList<>();

        for (Question question : questions) {
            QuestionContestResponse dto = QuestionContestResponse.builder()
                    .questionId(question.getId())
                    .contestId(question.getContest().getId())
                    .contestName(question.getContest().getTitle())
                    .questionTitle(question.getTitle()).build();
            questionLists.add(dto);
        }
        return new QuestionContestList(questionLists);
    }

    @Transactional
    public void modifyQuestion(Long id, QuestionTitleRequest request) {
        Question question = questionFacade.findById(id);
        User user = userFacade.getCurrentUser();
        if (!question.getUser().getId().equals(user.getId())) {
            throw UserMissMatchedException.EXCEPTION;
        }
        question.setQuestion(request.getTitle());
    }

    @Transactional
    public void questionAnswer(Long id, QuestionContentRequest request) {
        Question question = questionFacade.findById(id);
        User user = userFacade.getCurrentUser();
        if (!user.getRole().equals(Role.ADMIN)) {
            throw UserMissMatchedException.EXCEPTION;
        }
        question.answerQuestion(request.getContent());
    }

}
