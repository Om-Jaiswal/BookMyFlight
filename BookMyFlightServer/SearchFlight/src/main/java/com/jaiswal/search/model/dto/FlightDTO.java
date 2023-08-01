package com.jaiswal.search.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDTO {

	private String flightNumber;
	private String departureTime;
	private String arrivalTime;
	private String airline;
	private String airlineImage;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;
	private String source;
	private String destination;
	private String status;
	private double[] prices;
	
}
