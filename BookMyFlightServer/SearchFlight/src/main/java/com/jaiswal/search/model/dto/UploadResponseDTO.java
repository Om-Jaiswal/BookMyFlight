package com.jaiswal.search.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadResponseDTO {
	
    private boolean success;
    private String message;
    private String imageUrl;

}