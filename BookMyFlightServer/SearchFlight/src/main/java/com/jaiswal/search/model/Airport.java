package com.jaiswal.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="airports")
public class Airport { 
	
	@Id
	private String name;
	
	private String city;
	
	private String country;
	
	private String IATA;
	
	private String ICAO;
	
	private double lat;
	
	private double lon;
	
	private String timezone;
	
}
