package com.jaiswal.fms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="contacts")
public class Contact {
	
	@Transient
    public static final String SEQUENCE_NAME = "contact_sequence";
	
	@Id
	private long contactId;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String message;
	
}
