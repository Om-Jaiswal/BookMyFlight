package com.jaiswal.search.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.jaiswal.search.model.dto.UploadResponseDTO;

public class SearchUtils {
	
    public static ResponseEntity<UploadResponseDTO> uploadImage(MultipartFile image) {
        try {
            if (image.isEmpty()) {
                return ResponseEntity.badRequest().body(new UploadResponseDTO(false, "Empty Image!", null));
            }

            String fileName = image.getOriginalFilename();
            assert fileName != null;
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));

            // Generate a unique filename to prevent overwriting existing files
            String uniqueFileName = System.currentTimeMillis() + fileExtension;

            // Path where the file will be saved
            Path filePath = Path.of("src/main/resources/static/uploads/", uniqueFileName);

            // Copy the file to the target location
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the URL of the uploaded file
            String fileUrl = "/uploads/" + uniqueFileName;
            return ResponseEntity.ok().body(new UploadResponseDTO(true, "Image Uploaded Successfully!", fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResponseDTO(false, "An error occurred during image upload!", null));
        }
    }

}
