package com.jaiswal.search.service;

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

import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.repository.AirportRepository;
import com.jaiswal.search.repository.FlightRepository;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplementationTest {
	
	private Airport airport;
	private List<Airport> airports;
	
	private Flight flight;
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
		airports = new ArrayList<Airport>();
		airports.add(airport);
		
		flight = new Flight("ABC123", "5:00 AM", "8:00 AM",  "Delta", "On-time");
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

}
