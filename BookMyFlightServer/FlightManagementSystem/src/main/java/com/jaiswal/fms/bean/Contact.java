package com.jaiswal.fms.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="contacts")
public class Contact {
	
	@Id
	private String contactId;
	private String name;
	private String email;
	private String message;
	
}
