package com.jaiswal.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightCapacityDTO {
	
	private String flightNumber;
	private boolean[][] standard;
	private boolean[][] economy;
	private boolean[][] business;
	
}
