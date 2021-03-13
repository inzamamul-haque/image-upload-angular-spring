package com.inzamamul.haque.controller;

import com.inzamamul.haque.dto.response.FileUploadResponse;
import com.inzamamul.haque.exception.ResourceNotFoundException;
import com.inzamamul.haque.service.ImageStorageService;
import com.inzamamul.haque.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("image/")
public class ImageUploadController {
    @Autowired
    ImageUploadService imageUploadService;

    @Autowired
    ImageStorageService imageStorageService;

    @PostMapping("upload")
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) {
        FileUploadResponse fileUploadResponse = imageUploadService.upload(file);
        System.out.println(fileUploadResponse.getName());
        return new ResponseEntity<FileUploadResponse>(fileUploadResponse, HttpStatus.OK);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = imageStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new ResourceNotFoundException("Image Not Found");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
