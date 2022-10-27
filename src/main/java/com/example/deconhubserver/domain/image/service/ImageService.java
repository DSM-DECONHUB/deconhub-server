package com.example.deconhubserver.domain.image.service;

import com.example.deconhubserver.domain.image.dto.ImageListResponse;
import com.example.deconhubserver.infrastucture.s3.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageFacade imageFacade;

    @Transactional
    public ImageListResponse imageUpload(List<MultipartFile> images) {

        List<String> image = images.stream()
                .map(imageFacade::upload)
                .collect(Collectors.toList());

        return new ImageListResponse(image);
    }
}
