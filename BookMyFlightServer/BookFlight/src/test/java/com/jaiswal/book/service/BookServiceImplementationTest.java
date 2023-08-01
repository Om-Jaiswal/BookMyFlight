package com.jaiswal.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.jaiswal.book.exception.InvalidFlightClassException;
import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.model.dto.BookingDTO;
import com.jaiswal.book.model.dto.FlightCapacityDTO;
import com.jaiswal.book.model.dto.PassengerDTO;
import com.jaiswal.book.model.dto.PlaceDTO;
import com.jaiswal.book.repository.BookingRepository;
import com.jaiswal.book.repository.FlightCapacityRepository;
import com.jaiswal.book.util.BookingUtils;

@ExtendWith(MockitoExtension.class)
class BookServiceImplementationTest {
	
	private FlightCapacityDTO flightCapacityDTO;
	private FlightCapacityDTO invalidFlightCapacityDTO;
	private FlightCapacity flightCapacity;
	private Optional<FlightCapacity> optionalFlightCapacity;
	private FlightCapacity updatedFlightCapacity;
	private BookingDTO invalidBookingDTO;
	private BookingDTO bookingDTO;
	private Booking booking;
	private Optional<Booking> optionalBooking;
	private Booking updatedBooking;
	private PlaceDTO source;
	private PlaceDTO destination;
	private List<PassengerDTO> passengerDetails;
	private List<String> seats;
	private List<Booking> bookings;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private BookingUtils bookingUtils;
	
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
		flightCapacityDTO = new FlightCapacityDTO("ABC123", capacity, capacity, capacity);
		invalidFlightCapacityDTO = new FlightCapacityDTO("000000", capacity, capacity, capacity);
		flightCapacity = new FlightCapacity("ABC123", capacity, capacity, capacity);
		optionalFlightCapacity = Optional.of(flightCapacity);
		updatedFlightCapacity = new FlightCapacity("ABC123", updatedCapacity, capacity, capacity);
		
		source = new PlaceDTO("NAG", "Nagpur", "Dr. Babasaheb Ambedkar Intl");
		destination = new PlaceDTO("BOM", "Mumbai", "Chhatrapati Shivaji Intl");
		passengerDetails = new ArrayList<>();
		passengerDetails.add(new PassengerDTO("John Doe", 28, "Male"));
		seats = new ArrayList<>();
		seats.add("A1");
		bookingDTO = new BookingDTO(1001L, "ABC123", "Delta", "5:00 AM", "8:00 AM", "01-07-2023", source, destination, 2, passengerDetails, seats, "Standard", 314790, "johndoe");
		invalidBookingDTO = new BookingDTO(1000L, "", "", "", "", "", null, null, 0, null, null, "", 0, "");
		booking = new Booking(1001L, "ABC123", "Delta", "5:00 AM", "8:00 AM", "01-07-2023", source, destination, 2, passengerDetails, seats, "Standard", 314790, "johndoe");
		optionalBooking = Optional.of(booking);
		updatedBooking = new Booking(1001L, "ABC123", "Delta", "5:30 AM", "8:30 AM", "01-07-2023", source, destination, 2, passengerDetails, seats, "Standard", 314790, "johndoe");
		bookings = new ArrayList<>();
		bookings.add(booking);
	}

	@Test
	void addFlightCapacityTest() {
		when(modelMapper.map(flightCapacityDTO, FlightCapacity.class)).thenReturn(flightCapacity);
		when(flightCapacityRepository.save(flightCapacity)).thenReturn(flightCapacity);
		assertEquals(ResponseEntity.ok().body("Flight Capacity Added Successfully!"), service.addFlightCapacity(flightCapacityDTO));
	}
	
	@Test
	void updateFlightCapacityTest() {
		when(flightCapacityRepository.findById("ABC123")).thenReturn(optionalFlightCapacity);
		doReturn(updatedFlightCapacity).when(flightCapacityRepository).save(any(FlightCapacity.class));
		assertEquals(ResponseEntity.ok().body("Flight Capacity Updated Successfully!"), service.updateFlightCapacity(flightCapacityDTO));
	}
	
	@Test
	void updateFlightCapacityFlightNotFoundTest() {
		when(flightCapacityRepository.findById("000000")).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.updateFlightCapacity(invalidFlightCapacityDTO));
	}
	
	@Test
	void getFlightCapacityTest() {
		when(flightCapacityRepository.findByFlightNumber("ABC123")).thenReturn(flightCapacity);
		assertEquals(flightCapacity, service.getFlightCapacity("ABC123"));
	}
	
	@Test
	void deleteFlightCapacityTest() {
		when(flightCapacityRepository.findById("ABC123")).thenReturn(optionalFlightCapacity);
		doNothing().when(flightCapacityRepository).deleteById("ABC123");
		assertEquals(ResponseEntity.ok().body("Flight Capacity Deleted Successfully!"), service.deleteFlightCapacity("ABC123"));
	}
	
	@Test
	void deleteFlightCapacityFlightNotFoundTest() {
		when(flightCapacityRepository.findById("000000")).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.deleteFlightCapacity("000000"));
	}
	
	@Test
	void addBookingTest() throws InvalidFlightClassException {
		when(modelMapper.map(bookingDTO, Booking.class)).thenReturn(booking);
		when(flightCapacityRepository.findByFlightNumber("ABC123")).thenReturn(flightCapacity);
		doReturn(updatedFlightCapacity).when(flightCapacityRepository).save(any());
		when(bookingRepository.save(booking)).thenReturn(booking);
		when(bookingUtils.publishMessage(any(), any())).thenReturn("Payment Published!");
		assertEquals(ResponseEntity.ok().body("Booking Added Successfully!"), service.addBooking(bookingDTO));
	}
	
	@Test
	void updateBookingTest() {
		when(bookingRepository.findById(1001L)).thenReturn(optionalBooking);
		doReturn(updatedBooking).when(bookingRepository).save(any(Booking.class));
		assertEquals(ResponseEntity.ok().body("Booking Updated Successfully!"), service.updateBooking(bookingDTO));
	}
	
	@Test
	void updateBookingBookingNotFoundTest() {
		when(bookingRepository.findById(1000L)).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.updateBooking(invalidBookingDTO));
	}
	
	@Test
	void getBookingTest() {
		when(bookingRepository.findByBookingId(1001L)).thenReturn(booking);
		assertEquals(booking, service.getBooking(1001L));
	}

	@Test
	void getBookingsTest() {
		when(bookingRepository.findByPaidBy("johndoe")).thenReturn(bookings);
		assertEquals(bookings, service.getBookings("johndoe"));
	}
	
	@Test
	void deleteBookingTest() {
		when(bookingRepository.findById(1001L)).thenReturn(optionalBooking);
		doNothing().when(bookingRepository).deleteById(1001L);
		assertEquals(ResponseEntity.ok().body("Booking Deleted Successfully!"), service.deleteBooking(1001L));
	}
	
	@Test
	void deleteBookingBookingNotFoundTest() {
		when(bookingRepository.findById(1000L)).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.deleteBooking(1000L));
	}
	
}
