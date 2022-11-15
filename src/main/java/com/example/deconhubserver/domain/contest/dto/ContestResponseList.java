package com.example.deconhubserver.domain.contest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ContestResponseList {
    private final List<ContestList> list;
}
