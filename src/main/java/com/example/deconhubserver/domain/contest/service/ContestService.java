package com.example.deconhubserver.domain.contest.service;

import com.example.deconhubserver.domain.contest.dto.ContestList;
import com.example.deconhubserver.domain.contest.dto.ContestRequest;
import com.example.deconhubserver.domain.contest.dto.ContestResponse;
import com.example.deconhubserver.domain.contest.entity.Contest;
import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import com.example.deconhubserver.domain.contest.exception.UserMissMatchedException;
import com.example.deconhubserver.domain.contest.facade.ContestFacade;
import com.example.deconhubserver.domain.contest.repository.ContestRepository;
import com.example.deconhubserver.domain.user.entity.User;
import com.example.deconhubserver.domain.user.enums.Role;
import com.example.deconhubserver.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContestService {

    private final ContestRepository contestRepository;
    private final ContestFacade contestFacade;
    private final UserFacade userFacade;

    @Transactional // 대회 생성
    public void createContest(ContestRequest request) {
        User user = userFacade.getCurrentUser();
        Contest contest = new Contest(
                request.getTitle(),
                request.getIntroduce(),
                request.getPeriod(),
                request.getPlace(),
                request.getSignPeriod(),
                request.getSponsor(),
                request.getSiteAddress(),
                request.getSignCondition(),
                request.getSignWay(),
                request.getHistory(),
                request.getTopic(),
                request.getCategory(),
                user
        );

        contestRepository.save(contest);
    }

    @Transactional // 대회 수정
    public void setContest(Long contestId, ContestRequest request) {
        Contest contest = contestFacade.findById(contestId);
        userMatch(contest);
        contest.setContest(request);
    }

    @Transactional // 대회 삭제
    public void delContest(Long contestId) {
        Contest contest = contestFacade.findById(contestId);
        userMatch(contest);
        contestRepository.delete(contest);
    }

    @Transactional(readOnly = true) // 대회 전체보기
    public List<ContestList> contestList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Contest> contests = contestFacade.findAllById(sort);
        List<ContestList> contestLists = new ArrayList<>();

        for (Contest contest : contests) {
            ContestList dto = ContestList.builder()
                    .title(contest.getTitle())
                    .period(contest.getPeriod())
                    .dateTime(contest.getCreatePeriod().until(contest.getSignPeriod(), ChronoUnit.DAYS) + 1)
                    .topic(contest.getTopic())
                    .category(contest.getCategory()).build();
            contestLists.add(dto);
        }
        return contestLists;

    }

    @Transactional(readOnly = true) // 대회 필터 보기
    public List<ContestList> categoryList(ContestCategory category) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Contest> contests = contestFacade.findAllById(sort);
        List<ContestList> contestLists = new ArrayList<>();

        for (Contest contest : contests) {
            if (contest.getCategory().equals(category)) {
                ContestList dto = ContestList.builder()
                        .title(contest.getTitle())
                        .period(contest.getPeriod())
                        .dateTime(contest.getCreatePeriod().until(contest.getSignPeriod(), ChronoUnit.DAYS) + 1)
                        .topic(contest.getTopic())
                        .category(contest.getCategory()).build();
                contestLists.add(dto);
            }
        }
        return contestLists;
    }

    @Transactional(readOnly = true) // 대회 특정 날짜 필터 보기
    public List<ContestList> dateList(Long date) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Contest> contests = contestFacade.findAllById(sort);
        List<ContestList> contestLists = new ArrayList<>();

        for (Contest contest : contests) {
            if (contest.getSignPeriod().getYear() + contest.getSignPeriod().getDayOfMonth() + contest.getSignPeriod().getMonthValue() == date) {
                ContestList dto = ContestList.builder()
                        .title(contest.getTitle())
                        .period(contest.getPeriod())
                        .dateTime(contest.getCreatePeriod().until(contest.getSignPeriod(), ChronoUnit.DAYS) + 1)
                        .topic(contest.getTopic())
                        .category(contest.getCategory()).build();
                contestLists.add(dto);
            }
        }
        return contestLists;
    }

    @Transactional(readOnly = true) // 대회 상세보기
    public ContestResponse contestDetail(Long contestId){
        Contest contest = contestFacade.findById(contestId);

        return ContestResponse.builder()
                .title(contest.getTitle())
                .introduce(contest.getIntroduce())
                .period(contest.getPeriod())
                .place(contest.getPlace())
                .dateTime(contest.getCreatePeriod().until(contest.getSignPeriod(), ChronoUnit.DAYS) + 1)
                .sponsor(contest.getSponsor())
                .siteAddress(contest.getSiteAddress())
                .signCondition(contest.getSignCondition())
                .signWay(contest.getSignWay())
                .history(contest.getHistory())
                .topic(contest.getTopic())
                .category(contest.getCategory()).build();

    }

    private void userMatch(Contest contest){
        if (contest.getUser().getAccountId().equals(userFacade.getCurrentUser().getAccountId()) || userFacade.getCurrentUser().getRole() == Role.ADMIN){
            ;
        }else throw UserMissMatchedException.EXCEPTION;
    }
}
