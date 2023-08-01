package com.jaiswal.fms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="offers")
public class Offer {
	
	@Id
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String airline;
}
