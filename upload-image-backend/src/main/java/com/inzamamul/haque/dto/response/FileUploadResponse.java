package com.inzamamul.haque.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {
    private String name;
    private String type;
    private String imageUrl;
}
