package com.jaiswal.search.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
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
	@NotBlank
	private String departureTime;
	@NotBlank
	private String arrivalTime;
	@NotBlank
	private String airline;
	@NotBlank
	private String airlineImage;
	@NotBlank
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date date;
	@NotBlank
	private String source;
	@NotBlank
	private String destination;
	@NotBlank
	private String status;
	@NotBlank
	private double[] prices;
	
}
