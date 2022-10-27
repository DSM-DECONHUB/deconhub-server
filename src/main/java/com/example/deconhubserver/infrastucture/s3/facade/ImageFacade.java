package com.example.deconhubserver.infrastucture.s3.facade;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.deconhubserver.infrastucture.s3.exception.ImageNotFoundException;
import com.example.deconhubserver.infrastucture.s3.exception.InvalidImageExtensionFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ImageFacade {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    public String upload(MultipartFile multipartFile) {
        String extension = getExtension(multipartFile);
        String imageUrl = "${UUID.randomUUID()} $extension";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try {
            amazonS3Client.putObject(
                    new PutObjectRequest("test", imageUrl, multipartFile.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw ImageNotFoundException.EXCEPTION;
        }

        return amazonS3Client.getUrl("test", imageUrl).toString();
    }

    private String getExtension(MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null) {
            throw ImageNotFoundException.EXCEPTION;
        }

        String originalName = multipartFile.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();

        if (!(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".heic"))) {
            throw InvalidImageExtensionFormatException.EXCEPTION;
        }

        return extension;
    }
}
