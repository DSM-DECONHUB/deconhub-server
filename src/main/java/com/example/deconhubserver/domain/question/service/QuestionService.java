package com.example.deconhubserver.domain.question.service;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.contest.facade.ContestFacade;
import com.example.deconhubserver.domain.question.dto.QuestionList;
import com.example.deconhubserver.domain.question.dto.QuestionRequest;
import com.example.deconhubserver.domain.question.dto.QuestionResponse;
import com.example.deconhubserver.domain.question.entity.Question;
import com.example.deconhubserver.domain.question.facade.QuestionFacade;
import com.example.deconhubserver.domain.question.repository.QuestionRepository;
import com.example.deconhubserver.domain.user.entity.User;
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
    public void createQuestion(QuestionRequest request, Long id){
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
    public List<QuestionList> questionList(Long id){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Question> questions = questionFacade.findAllById(sort);
        List<QuestionList> questionLists = new ArrayList<>();

        for(Question question : questions){
            if(question.getContest().getId().equals(id)){
                QuestionList dto = QuestionList.builder()
                        .title(question.getTitle()).build();
                questionLists.add(dto);
            }
        }
        return questionLists;
    }

    @Transactional(readOnly = true)
    public QuestionResponse questionDetail(Long id){
        Question question = questionFacade.findById(id);
        return QuestionResponse.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .accountId(question.getUser().getAccountId()).build();
    }
}
