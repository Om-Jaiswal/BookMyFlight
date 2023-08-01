package com.jaiswal.book.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jaiswal.book.exception.InvalidFlightClassException;
import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.model.dto.BookingDTO;
import com.jaiswal.book.model.dto.FlightCapacityDTO;

public interface BookService {
	
	public ResponseEntity<String> addFlightCapacity(FlightCapacityDTO flightCapacity);
	public ResponseEntity<String> updateFlightCapacity(FlightCapacityDTO flightCapacity);
	public FlightCapacity getFlightCapacity(String flightNumber);
	public ResponseEntity<String> deleteFlightCapacity(String flightNumber);
	public ResponseEntity<String> addBooking(BookingDTO booking) throws InvalidFlightClassException;
	public ResponseEntity<String> updateBooking(BookingDTO booking);
	public Booking getBooking(long bookingId);
	public List<Booking> getBookings(String paidBy);
	public List<Booking> getAllBookings();
	public ResponseEntity<String> deleteBooking(long bookingId);
	
}
