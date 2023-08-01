package com.jaiswal.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
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
	@NotBlank
	private String city;
	@NotBlank
	private String country;
	@NotBlank
	private String IATA;
	@NotBlank
	private String ICAO;
	@NotBlank
	private double lat;
	@NotBlank
	private double lon;
	@NotBlank
	private String timezone;
	
}
