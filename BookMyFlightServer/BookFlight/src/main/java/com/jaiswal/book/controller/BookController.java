package com.jaiswal.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.book.exception.InvalidFlightClassException;
import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.model.dto.BookingDTO;
import com.jaiswal.book.model.dto.FlightCapacityDTO;
import com.jaiswal.book.service.BookService;

@RestController
@RequestMapping("/book-flights")
public class BookController {

	@Autowired
	private BookService service;
	
	/**
	 * Add flight capacity using service addFlightCapacity method
	 * @param flightCapacity - FlightCapacityDTO object
	 * @return success or failure string
	 */
	@PostMapping("/add-flight-capacity")
	public ResponseEntity<String> addFlightCapacity(@RequestBody FlightCapacityDTO flightCapacity) {
		return service.addFlightCapacity(flightCapacity);
	}
	
	/**
	 * Update flight capacity using service updateFlightCapacity method
	 * @param flightCapacity - FlightCapacityDTO object
	 * @return success or failure string
	 */
	@PutMapping("/update-flight-capacity")
	public ResponseEntity<String> updateFlightCapacity(@RequestBody FlightCapacityDTO flightCapacity) {
		return service.updateFlightCapacity(flightCapacity);
	}
	
	/**
	 * Get flight capacity using service getFlightCapacity method
	 * @param flightNumber - string
	 * @return FlightCapacity object
	 */
	@GetMapping("/get-flight-capacity")
	public FlightCapacity getFlightCapacity(@RequestParam("flightNumber") String flightNumber) {
		return service.getFlightCapacity(flightNumber);
	}
	
	/**
	 * Delete flight capacity using service deleteFlightCapacity method
	 * @param flightNumber - string
	 * @return success or failure string
	 */
	@DeleteMapping("/delete-flight-capacity")
	public ResponseEntity<String> deleteFlightCapacity(@RequestParam("flightNumber") String flightNumber) {
		return service.deleteFlightCapacity(flightNumber);
	}
	
	/**
	 * Add booking using service addBooking method
	 * @param booking - BookingDTO object
	 * @return success or failure string
	 */
	@PostMapping("/add-booking")
	public ResponseEntity<String> addBooking(@RequestBody BookingDTO booking) throws InvalidFlightClassException {
		return service.addBooking(booking);
	}
	
	/**
	 * Update booking using service updateBooking method
	 * @param booking - BookingDTO object
	 * @return success or failure string
	 */
	@PutMapping("/update-booking")
	public ResponseEntity<String> updateBooking(@RequestBody BookingDTO booking) {
		return service.updateBooking(booking);
	}
	
	/**
	 * Get booking using service getBooking method
	 * @param bookingId - string
	 * @return Booking object
	 */
	@GetMapping("/get-booking")
	public Booking getBooking(@RequestParam("bookingId") long bookingId) {
		return service.getBooking(bookingId);
	}
	
	/**
	 * Get list of bookings using service getBookings method
	 * @param paidBy - string
	 * @return list of Booking objects
	 */
	@GetMapping("/get-bookings")
	public List<Booking> getBookings(@RequestParam("paidBy") String paidBy) {
		return service.getBookings(paidBy);
	}
	
	/**
	 * Get list of all bookings using service getAllBookings method
	 * @return list of Booking objects
	 */
	@GetMapping("/get-all-bookings")
	public List<Booking> getAllBookings() {
		return service.getAllBookings();
	}
	
	/**
	 * Delete booking using service deleteBooking method
	 * @param bookingId - string
	 * @return success or failure string
	 */
	@DeleteMapping("/delete-booking")
	public ResponseEntity<String> deleteBooking(@RequestParam("bookingId") long bookingId) {
		return service.deleteBooking(bookingId);
	}
	
}
