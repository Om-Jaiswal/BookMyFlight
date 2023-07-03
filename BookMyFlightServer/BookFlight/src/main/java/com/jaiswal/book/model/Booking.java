package com.jaiswal.book.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="bookings")
public class Booking {
	
	@Id
	private String bookingId;
	private String flightNumber;
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private String date;
    private Place source;
    private Place destination;
    private int passengerCount;
    private List<Passenger> passengerDetails;
    private List<String> seats;
    private String flightClass;
    private double amountPaid;
    private String paidBy;
}
