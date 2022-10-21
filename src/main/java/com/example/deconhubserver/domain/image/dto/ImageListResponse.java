package com.example.deconhubserver.domain.image.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ImageListResponse {
    List<String> imageUrlList;
}
