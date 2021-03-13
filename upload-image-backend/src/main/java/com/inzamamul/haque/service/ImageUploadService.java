package com.inzamamul.haque.service;

import com.inzamamul.haque.dto.response.FileUploadResponse;
import com.inzamamul.haque.entity.ImageData;
import com.inzamamul.haque.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Service
public class ImageUploadService {
    @Autowired
    ImageStorageService imageStorageService;

    @Autowired
    ImageDataRepository imageDataRepository;

    public ImageUploadService() {
    }

    public FileUploadResponse upload(MultipartFile file) {
        /*Start upload image*/
        String fileName = null;
        String fileDownloadUri = null;

        if (!file.isEmpty()) {
            fileName = imageStorageService.storeFile(file, file.getOriginalFilename());
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/download/")
                    .path(fileName)
                    .toUriString();
        } else {
            System.err.println("File Not found");
        }

        ImageData imageDetails = new ImageData();
        imageDetails.setImageName(fileName);
        imageDetails.setImageType(file.getContentType());
        imageDetails.setUrlPath(fileDownloadUri);
        /*End upload image*/
        imageDataRepository.save(imageDetails);
        return new FileUploadResponse(fileName, file.getContentType(), fileDownloadUri);
    }
}
