package com.jaiswal.book.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;

public interface BookService {
	public String addFlightCapacity(FlightCapacity flightCapacity);
	public FlightCapacity getFlightCapacity(String flightNumber);
	public ResponseEntity<String> addBooking(Booking booking);
	public List<Booking> getBookings(String paidBy);
}
