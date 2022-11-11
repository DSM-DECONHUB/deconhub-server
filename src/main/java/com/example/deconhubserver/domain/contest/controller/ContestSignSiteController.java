package com.example.deconhubserver.domain.contest.controller;

import com.example.deconhubserver.domain.contest.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contest")
@RequiredArgsConstructor
public class ContestSignSiteController {
    private final ContestService contestService;

    @GetMapping("/signSite/{contestId}")
    public String signSite(@PathVariable(name = "contestId") Long id){
        return contestService.signSite(id);
    }
}
