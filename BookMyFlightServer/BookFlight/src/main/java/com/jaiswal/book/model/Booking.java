package com.jaiswal.book.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jaiswal.book.model.dto.PassengerDTO;
import com.jaiswal.book.model.dto.PlaceDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="bookings")
public class Booking {
	
	@Transient
    public static final String SEQUENCE_NAME = "booking_sequence";
	
	@Id
	private long bookingId;
	@NotBlank
	private String flightNumber;
	@NotBlank
    private String airline;
	@NotBlank
    private String departureTime;
	@NotBlank
    private String arrivalTime;
	@NotBlank
    private String date;
	@NotBlank
    private PlaceDTO source;
	@NotBlank
    private PlaceDTO destination;
	@NotBlank
    private int passengerCount;
	@NotBlank
    private List<PassengerDTO> passengerDetails;
	@NotBlank
    private List<String> seats;
	@NotBlank
    private String flightClass;
	@NotBlank
    private double amountPaid;
	@NotBlank
    private String paidBy;
    
}
