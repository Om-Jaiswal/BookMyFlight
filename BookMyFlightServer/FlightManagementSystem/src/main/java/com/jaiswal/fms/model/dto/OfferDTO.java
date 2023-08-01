package com.jaiswal.fms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OfferDTO {
	
	private String title;
	private String description;
	private String airline;
}
