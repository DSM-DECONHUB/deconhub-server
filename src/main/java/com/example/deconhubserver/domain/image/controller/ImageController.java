package com.example.deconhubserver.domain.image.controller;

import com.example.deconhubserver.domain.image.dto.ImageListResponse;
import com.example.deconhubserver.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImageListResponse upload(@RequestParam List<MultipartFile> images) {
        return imageService.imageUpload(images);
    }
}
