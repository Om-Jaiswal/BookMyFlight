package com.jaiswal.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.service.BookService;

@RestController
@RequestMapping("/book-flights")
public class BookController {

	@Autowired
	private BookService service;
	
	@PostMapping("/add-flight-capacity")
	public String addFlightCapacity(@RequestBody FlightCapacity flightCapacity) {
		return service.addFlightCapacity(flightCapacity);
	}
	
	@GetMapping("/get-flight-capacity")
	public FlightCapacity getFlightCapacity(@RequestParam("flightNumber") String flightNumber) {
		return service.getFlightCapacity(flightNumber);
	}
	
	@PostMapping("/add-booking")
	public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
		return service.addBooking(booking);
	}
	
	@GetMapping("/get-bookings")
	public List<Booking> getBookings(@RequestParam("paidBy") String paidBy) {
		return service.getBookings(paidBy);
	}
}
