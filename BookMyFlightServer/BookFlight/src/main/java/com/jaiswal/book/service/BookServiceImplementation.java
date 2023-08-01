package com.jaiswal.book.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jaiswal.book.exception.InvalidFlightClassException;
import com.jaiswal.book.model.Booking;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.model.dto.BookingDTO;
import com.jaiswal.book.model.dto.FlightCapacityDTO;
import com.jaiswal.book.model.dto.PaymentDTO;
import com.jaiswal.book.repository.BookingRepository;
import com.jaiswal.book.repository.FlightCapacityRepository;
import com.jaiswal.book.util.BookingUtils;

@Service
public class BookServiceImplementation implements BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImplementation.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private BookingUtils bookingUtils;
	
	@Autowired
	private FlightCapacityRepository flightCapacityRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	/**
	 * Method to add flight capacity in the database
	 * @param flightCapacity - FlightCapacityDTO object which will be added in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> addFlightCapacity(FlightCapacityDTO flightCapacity) {
		flightCapacityRepository.save(modelMapper.map(flightCapacity, FlightCapacity.class));
		logger.warn("Flight Capacity Added Successfully!");
		return ResponseEntity.ok().body("Flight Capacity Added Successfully!");
	}
	
	/**
	 * Method to update flight capacity
	 * @param flightCapacity - FlightCapacityDTO object which will be updated in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> updateFlightCapacity(FlightCapacityDTO flightCapacity) {
		Optional<FlightCapacity> optionalFlightCapacity = flightCapacityRepository.findById(flightCapacity.getFlightNumber());
		if (optionalFlightCapacity.isPresent()) {
			
			FlightCapacity existingflightCapacity = optionalFlightCapacity.get();
			
			existingflightCapacity.setStandard(flightCapacity.getStandard());
			existingflightCapacity.setEconomy(flightCapacity.getEconomy());
			existingflightCapacity.setBusiness(flightCapacity.getBusiness());

            flightCapacityRepository.save(existingflightCapacity);
            
            logger.warn("Flight Capacity Updated Successfully!");
            return ResponseEntity.ok().body("Flight Capacity Updated Successfully!");
        } else {
        	logger.warn("Flight doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
	/**
	 * Method to get flight capacity
	 * @param flightNumber - flightNumber by which the flight capacity will be searched
	 * @return flightCapacity - flight capacity
	 */
	public FlightCapacity getFlightCapacity(String flightNumber) {
		logger.warn("Flight Capacity Fetched!");
		return flightCapacityRepository.findByFlightNumber(flightNumber);
	}
	
	/**
	 * Method to delete flight capacity
	 * @param flightNumber - flightNumber by which the flight capacity will be deleted
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> deleteFlightCapacity(String flightNumber) {
		Optional<FlightCapacity> optionalFlightCapacity = flightCapacityRepository.findById(flightNumber);
		if (optionalFlightCapacity.isPresent()) {
			flightCapacityRepository.deleteById(flightNumber);
			logger.warn("Flight Capacity Deleted Successfully!");
			return ResponseEntity.ok().body("Flight Capacity Deleted Successfully!");
		} else {
        	logger.warn("Flight doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
	/**
	 * Method to add booking in the database
	 * @param booking - BookingDTO object which will be added in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> addBooking(BookingDTO booking) throws InvalidFlightClassException {
		Booking currentBooking = modelMapper.map(booking, Booking.class);
		
		String flightClass = booking.getFlightClass();
		
		List<String> seats = booking.getSeats();
		
		String flightNumber = booking.getFlightNumber();
		FlightCapacity flightCapacity = flightCapacityRepository.findByFlightNumber(flightNumber);
		
		FlightCapacity updatedFlightCapacity = bookingUtils.updateSeats(flightClass, seats, flightCapacity);
		
		flightCapacityRepository.save(updatedFlightCapacity);
		
		currentBooking.setBookingId(bookingUtils.generateSequence(Booking.SEQUENCE_NAME, mongoOperations));
		bookingRepository.save(currentBooking);
		
		String response = bookingUtils.publishMessage(template, new PaymentDTO(booking.getBookingId(), booking.getAmountPaid(), booking.getPaidBy(), new Date()));
		logger.warn(response);
		
		logger.warn("Booking Added Successfully!");
		return ResponseEntity.ok().body("Booking Added Successfully!");
	}
	
	/**
	 * Method to update booking
	 * @param booking - Booking object which will be updated in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> updateBooking(BookingDTO booking) {
		Optional<Booking> optionalBooking = bookingRepository.findById(booking.getBookingId());
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();
            
            existingBooking.setFlightNumber(booking.getFlightNumber());
            existingBooking.setAirline(booking.getAirline());
            existingBooking.setDepartureTime(booking.getDepartureTime());
            existingBooking.setArrivalTime(booking.getArrivalTime());
            existingBooking.setDate(booking.getDate());
            existingBooking.setSource(booking.getSource());
            existingBooking.setDestination(booking.getDestination());
            existingBooking.setPassengerCount(booking.getPassengerCount());
            existingBooking.setPassengerDetails(booking.getPassengerDetails());
            existingBooking.setSeats(booking.getSeats());
            existingBooking.setFlightClass(booking.getFlightClass());
            existingBooking.setAmountPaid(booking.getAmountPaid());
            existingBooking.setPaidBy(booking.getPaidBy());

            bookingRepository.save(existingBooking);
            logger.warn("Booking Updated Successfully!");
            return ResponseEntity.ok().body("Booking Updated Successfully!");
        } else {
        	logger.warn("Booking doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
	/**
	 * Method to get booking
	 * @param bookingId - bookingId by which the booking will be searched
	 * @return booking - booking
	 */
	public Booking getBooking(long bookingId) {
		logger.warn("Booking Fetched!");
		return bookingRepository.findByBookingId(bookingId);
	}
	
	/**
	 * Method to get list of bookings
	 * @param paidBy - paidBy by which the bookings will be searched
	 * @return bookings - list of all bookings
	 */
	public List<Booking> getBookings(String paidBy) {
		logger.warn("Bookings Fetched!");
		return bookingRepository.findByPaidBy(paidBy);
	}
	
	/**
	 * Method to get list of all bookings
	 * @return bookings - list of all bookings
	 */
	public List<Booking> getAllBookings() {
		logger.warn("Bookings Fetched!");
		return bookingRepository.findAll();
	}
	
	/**
	 * Method to delete booking
	 * @param bookingId - bookingId by which the booking will be deleted
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> deleteBooking(long bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
			bookingRepository.deleteById(bookingId);
			logger.warn("Booking Deleted Successfully!");
			return ResponseEntity.ok().body("Booking Deleted Successfully!");
        } else {
        	logger.warn("Booking doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
}
