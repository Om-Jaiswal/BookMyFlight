package com.jaiswal.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jaiswal.book.exception.FlightClassException;
import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.repository.BookingRepository;
import com.jaiswal.book.repository.FlightCapacityRepository;

@Service
public class BookServiceImplementation implements BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImplementation.class);
	
	@Autowired
	private FlightCapacityRepository flightCapacityRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	/**
	 * Method to add flight capacity in the database
	 * @param flightCapacity - flightCapacity object which will be added in the database
	 * @return message - confirmation message
	 */
	public String addFlightCapacity(FlightCapacity flightCapacity) {
		flightCapacityRepository.save(flightCapacity);
		return "Flight Capacity Added Successfully!";
	}
	
	/**
	 * Method to get flight capacity
	 * @param flightNumber - flightNumber by which the flight capacity will be searched
	 * @return flightCapacity - flight capacity
	 */
	public FlightCapacity getFlightCapacity(String flightNumber) {
		return flightCapacityRepository.findByFlightNumber(flightNumber);
	}
	
	/**
	 * Method to add booking in the database
	 * @param booking - booking object which will be added in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> addBooking(Booking booking) {
		String flightNumber = booking.getFlightNumber();
		FlightCapacity flightCapacity = flightCapacityRepository.findByFlightNumber(flightNumber);
		
		try {
			String flightClass = booking.getFlightClass();
			boolean[][] classCapacity = flightCapacity.getClassCapacity(flightClass);
			List<String> seats = booking.getSeats();
			for(String seat: seats) {
				if('A' == seat.charAt(0)) {
					classCapacity[0][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else if('B' == seat.charAt(0)) {
					classCapacity[1][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else if('C' == seat.charAt(0)) {
					classCapacity[2][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else if('D' == seat.charAt(0)) {
					classCapacity[3][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else {
					logger.warn("Row Doesn't Exist!");
				}
			}
			flightCapacity.setClassCapacity(flightClass, classCapacity);
		} catch(FlightClassException exception) {
			logger.warn(exception.getMessage());
		}
		
		flightCapacityRepository.save(flightCapacity);
		bookingRepository.save(booking);
		
		return ResponseEntity.ok().body("{\"message\": \"Booking Added Successfully!\"}");
	}
	
	/**
	 * Method to get list of bookings
	 * @param paidBy - paidBy by which the bookings will be searched
	 * @return bookings - list of all bookings
	 */
	public List<Booking> getBookings(String paidBy) {
		return bookingRepository.findByPaidBy(paidBy);
	}
}
