package com.jaiswal.fms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDTO {
	
	private long contactId;
	private String name;
	private String email;
	private String message;
	
}
