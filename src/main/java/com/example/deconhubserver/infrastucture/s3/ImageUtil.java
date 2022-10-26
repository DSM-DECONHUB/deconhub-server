package com.example.deconhubserver.infrastucture.s3;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {
    String upload(MultipartFile multipartFile);
}
