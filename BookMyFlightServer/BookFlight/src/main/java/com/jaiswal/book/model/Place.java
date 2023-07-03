package com.jaiswal.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Place {
	
	private String iataCode;
	private String airportCity;
	private String airportName;
}
