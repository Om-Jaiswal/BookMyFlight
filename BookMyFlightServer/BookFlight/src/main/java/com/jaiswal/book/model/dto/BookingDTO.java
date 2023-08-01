package com.jaiswal.book.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
	
	private long bookingId;
	private String flightNumber;
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private String date;
    private PlaceDTO source;
    private PlaceDTO destination;
    private int passengerCount;
    private List<PassengerDTO> passengerDetails;
    private List<String> seats;
    private String flightClass;
    private double amountPaid;
    private String paidBy;
    
}
