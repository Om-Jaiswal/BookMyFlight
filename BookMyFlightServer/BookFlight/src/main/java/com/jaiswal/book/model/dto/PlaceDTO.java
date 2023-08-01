package com.jaiswal.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceDTO {
	
	private String iataCode;
	private String airportCity;
	private String airportName;
	
}
