package com.example.deconhubserver.domain.contest.facade;

import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.contest.repository.ContestRepository;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.exception.ContestNotFoundException;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ContestFacade {
    private final ContestRepository contestRepository;

    public Contest findById(Long id){

        return contestRepository.findById(id)
                 .orElseThrow(() -> ContestNotFoundException.EXCEPTION);
    }

    public List<Contest> findAllById(Sort sort){
        return contestRepository.findAll(sort);

    }

}
