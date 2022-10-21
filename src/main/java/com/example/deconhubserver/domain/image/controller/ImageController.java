package com.example.deconhubserver.domain.image.controller;

import com.example.deconhubserver.domain.image.dto.ImageListResponse;
import com.example.deconhubserver.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @ResponseStatus(HttpStatus.CREATED)
    public ImageListResponse upload(@RequestParam List<MultipartFile> images) {
        return imageService.imageUpload(images);
    }
}
