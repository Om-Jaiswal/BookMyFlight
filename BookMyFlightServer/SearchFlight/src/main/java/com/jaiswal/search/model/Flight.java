package com.jaiswal.search.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;
	
	private String source;
	
	private String destination;
	
	private String status;
	
	private double[] prices;
	
}
