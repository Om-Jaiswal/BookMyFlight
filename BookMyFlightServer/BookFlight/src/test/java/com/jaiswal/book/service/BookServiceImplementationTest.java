package com.jaiswal.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.model.Passenger;
import com.jaiswal.book.model.Place;
import com.jaiswal.book.repository.BookingRepository;
import com.jaiswal.book.repository.FlightCapacityRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplementationTest {
	
	private FlightCapacity flightCapacity;
	private FlightCapacity updatedFlightCapacity;
	private Booking booking;
	private Place source;
	private Place destination;
	private List<Passenger> passengerDetails;
	private List<String> seats;
	private List<Booking> bookings;
	
	@Mock
	private FlightCapacityRepository flightCapacityRepository;
	
	@Mock
	private BookingRepository bookingRepository;

	@InjectMocks
	private BookServiceImplementation service;
	
	@BeforeEach
	public void setup() {
		boolean[][] capacity = {
				{false, false, false, false, false, false, false, false}, 
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}};
		boolean[][] updatedCapacity = {
				{true, false, false, false, false, false, false, false}, 
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}};
		flightCapacity = new FlightCapacity("ABC123", capacity, capacity, capacity);
		updatedFlightCapacity = new FlightCapacity("ABC123", updatedCapacity, capacity, capacity);
		
		source = new Place("NAG", "Nagpur", "Dr. Babasaheb Ambedkar Intl");
		destination = new Place("BOM", "Mumbai", "Chhatrapati Shivaji Intl");
		passengerDetails = new ArrayList<>();
		passengerDetails.add(new Passenger("John Doe", 28, "Male"));
		seats = new ArrayList<>();
		seats.add("A1");
		booking = new Booking("1", "ABC123", "Delta", "5:00 AM", "8:00 AM", "01-07-2023", source, destination, 2, passengerDetails, seats, "Standard", 314790, "johndoe");
		bookings = new ArrayList<>();
		bookings.add(booking);
	}

	@Test
	void addFlightCapacityTest() {
		when(flightCapacityRepository.save(flightCapacity)).thenReturn(flightCapacity);
		assertEquals("Flight Capacity Added Successfully!", service.addFlightCapacity(flightCapacity));
	}
	
	@Test
	void getFlightCapacityTest() {
		when(flightCapacityRepository.findByFlightNumber("ABC123")).thenReturn(flightCapacity);
		assertEquals(flightCapacity, service.getFlightCapacity("ABC123"));
	}
	
	@Test
	void addBookingTest() {
		when(flightCapacityRepository.findByFlightNumber("ABC123")).thenReturn(flightCapacity);
		when(flightCapacityRepository.save(flightCapacity)).thenReturn(updatedFlightCapacity);
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(ResponseEntity.ok().body("{\"message\": \"Booking Added Successfully!\"}"), service.addBooking(booking));
	}
	
	@Test
	void getBookingsTest() {
		when(bookingRepository.findByPaidBy("johndoe")).thenReturn(bookings);
		assertEquals(bookings, service.getBookings("johndoe"));
	}

}
