package com.jaiswal.search.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AirportDTO { 
	
	private String name;
	private String city;
	private String country;
	private String IATA;
	private String ICAO;
	private double lat;
	private double lon;
	private String timezone;
	
}
