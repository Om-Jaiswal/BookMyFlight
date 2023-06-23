package com.jaiswal.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.repository.AirportRepository;
import com.jaiswal.search.repository.FlightRepository;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplementationTest {
	
	Optional<Airport> optionalAirport;
	
	private Airport airport;
	private Airport updatedAirport;
	private List<Airport> airports;
	
	private Date date;
	Optional<Flight> optionalFlight;
	
	private Flight flight;
	private Flight updatedFlight;
	private List<Flight> flights;
	
	private List<String> result;
	
	@Mock
	private AirportRepository airportRepository;
	
	@Mock
	private FlightRepository flightRepository;

	@InjectMocks
	private SearchServiceImplementation service;
	
	@BeforeEach
	public void setup() {
		airport = new Airport("Chhatrapati Shivaji Intl ", "Mumbai", "India", "BOM", "VABB", 19.0886993408, 72.8678970337, "5.5");
		optionalAirport = Optional.ofNullable(airport);
		updatedAirport = new Airport("Chhatrapati Shivaji Intl ", "Mumbai", "India", "BOM", "VABB", 19.0886993408, 72.8678970337, "GMT + 5.5");
		airports = new ArrayList<Airport>();
		airports.add(airport);
		
		date = new Date();
		optionalFlight = Optional.ofNullable(flight);
		
		flight = new Flight("ABC123", "5:00 AM", "8:00 AM",  "Delta", date, "On-time", 1499.00);
		optionalFlight = Optional.ofNullable(flight);
		updatedFlight = new Flight("ABC123", "6:00 AM", "9:00 AM",  "Delta", date, "On-time", 1499.00);
		flights = new ArrayList<Flight>();
		flights.add(flight);
		
		result = new ArrayList<String>();
		result.add("BOM - Mumbai (Chhatrapati Shivaji Intl)");
	}

	@Test
	void addAirportTest() {
		when(airportRepository.save(airport)).thenReturn(airport);
		assertEquals("Airport Added Successfully!", service.addAirport(airport));
	}
	
	@Test
	void addAirportsTest() {
		when(airportRepository.saveAll(airports)).thenReturn(airports);
		assertEquals("Airports Added Successfully!", service.addAirports(airports));
	}
	
	@Test
	void addFlightTest() {
		when(flightRepository.save(flight)).thenReturn(flight);
		assertEquals("Flight Added Successfully!", service.addFlight(flight));
	}
	
	@Test
	void addFlightsTest() {
		when(flightRepository.saveAll(flights)).thenReturn(flights);
		assertEquals("Flights Added Successfully!", service.addFlights(flights));
	}
	
	@Test
	void getCitiesCodeTest() {
		when(airportRepository.findAll()).thenReturn(airports);
		assertEquals(result, service.getCitiesCode());
	}
	
	@Test
	void searchAirportsTest() {
		when(airportRepository.findAll()).thenReturn(airports);
		assertEquals(airports, service.searchAirports());
	}
	
	@Test
	void searchFlightsTest() {
		when(flightRepository.findAll()).thenReturn(flights);
		assertEquals(flights, service.searchFlights());
	}
	
	@Test
	void searchFlightByDateTest() {
		when(flightRepository.findByDate(date)).thenReturn(flights);
		assertEquals(flights, service.searchFlightByDate(date));
	}
	
	@Test
	void updateAirportTest() throws AirportNotFoundException {
		when(airportRepository.findById("Chhatrapati Shivaji Intl ")).thenReturn(optionalAirport);
		when(airportRepository.save(updatedAirport)).thenReturn(updatedAirport);
		assertEquals("Airport Updated Successfully!", service.updateAirport("Chhatrapati Shivaji Intl ", updatedAirport));
	}
	
	@Test
	void updateFlightTest() throws FlightNotFoundException {
		when(flightRepository.findById("ABC123")).thenReturn(optionalFlight);
		when(flightRepository.save(updatedFlight)).thenReturn(updatedFlight);
		assertEquals("Flight Updated Successfully!", service.updateFlight("ABC123", updatedFlight));
	}
	
	@Test
	void deleteAirportTest() throws AirportNotFoundException {
		when(airportRepository.findById("Chhatrapati Shivaji Intl ")).thenReturn(optionalAirport);
		doNothing().when(airportRepository).deleteById("Chhatrapati Shivaji Intl ");
		assertEquals("Airport Deleted Successfully!", service.deleteAirport("Chhatrapati Shivaji Intl "));
	}
	
	@Test
	void deleteFlightTest() throws FlightNotFoundException {
		when(flightRepository.findById("ABC123")).thenReturn(optionalFlight);
		doNothing().when(flightRepository).deleteById("ABC123");
		assertEquals("Flight Deleted Successfully!", service.deleteFlight("ABC123"));
	}

}
