package com.example.deconhubserver.domain.contest.controller;

import com.example.deconhubserver.domain.contest.dto.ContestList;
import com.example.deconhubserver.domain.contest.dto.ContestRequest;
import com.example.deconhubserver.domain.contest.dto.ContestResponse;
import com.example.deconhubserver.domain.contest.enums.ContestCategory;
import com.example.deconhubserver.domain.contest.service.ContestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "contset", description = "대회 관련 API 입니다.")
@RestController
@RequestMapping("/contest")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;

    @Operation(summary = "대회 전체보기")
    @GetMapping("/list")
    public List<ContestList> contestLists(){
        return contestService.contestList();
    }

    @Operation(summary = "대회 카테고리 필터보기")
    @GetMapping("/list/category")
    public List<ContestList> contestCategory(@RequestParam("value")ContestCategory category){
        return contestService.categoryList(category);
    }

    @Operation(summary = "대회 특정 날짜 필터보기")
    @GetMapping("/list/date")
    public List<ContestList> contestDate(@RequestParam("value")Long date){
        return contestService.dateList(date);
    }

    @Operation(summary = "대회 상세보기")
    @GetMapping("/list/detail/{contestId}")
    public ContestResponse contestDetail(@PathVariable Long contestId){
        return contestService.contestDetail(contestId);
    }

    @Operation(summary = "대회 삭제")
    @DeleteMapping("/delete/{contestId}")
    public void delContest(@PathVariable Long contestId){
        contestService.delContest(contestId);
    }

    @Operation(summary = "대회 생성")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createContest(@RequestBody ContestRequest request){
        contestService.createContest(request);
    }

    @Operation(summary = "대회 수정")
    @PutMapping("/modify/{contestId}")
    public void setContest(@PathVariable Long contestId, @RequestBody ContestRequest request){
        contestService.setContest(contestId, request);
    }
}
