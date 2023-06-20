package com.jaiswal.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="flights")
public class Flight {
	
	@Id
	private String flightNumber;
	private String departureTime;
	private String arrivalTime;
	private String airline;
	private String status;
}
