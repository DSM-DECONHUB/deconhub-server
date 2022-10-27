package com.example.deconhubserver.domain.question.service;

import com.example.deconhubserver.domain.question.dto.QuestionList;
import com.example.deconhubserver.domain.question.dto.QuestionRequest;
import com.example.deconhubserver.domain.question.dto.QuestionResponse;
import com.example.deconhubserver.domain.question.entity.Question;
import com.example.deconhubserver.domain.question.facade.QuestionFacade;
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
    private final UserFacade userFacade;

    @Transactional
    public void createQuestion(QuestionRequest request){
        User user = userFacade.getCurrentUser();
        Question question = new Question(
                user,
                request.getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<QuestionList> questionList(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Question> questions = questionFacade.findAllById(sort);
        List<QuestionList> questionLists = new ArrayList<>();

        for(Question question : questions){
            QuestionList dto = QuestionList.builder()
                    .title(question.getTitle()).build();
            questionLists.add(dto);
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
