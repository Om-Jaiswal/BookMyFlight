package com.jaiswal.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.model.dto.AirportDTO;
import com.jaiswal.search.model.dto.FlightDTO;
import com.jaiswal.search.repository.AirportRepository;
import com.jaiswal.search.repository.FlightRepository;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplementationTest {
	
	Optional<Airport> optionalAirport;
	
	private AirportDTO airportDTO;
	private Airport airport;
	private Airport updatedAirport;
	private AirportDTO updatedAirportDTO;
	private List<Airport> airports;
	private List<AirportDTO> airportsDTO;
	
	private Date date;
	private double[] prices;
	Optional<Flight> optionalFlight;
	
	private FlightDTO flightDTO;
	private Flight flight;
	private Flight updatedFlight;
	private FlightDTO updatedFlightDTO;
	private List<Flight> flights;
	private List<FlightDTO> flightsDTO;
	
	private List<String> result;
	private List<String> airlines;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private AirportRepository airportRepository;
	
	@Mock
	private FlightRepository flightRepository;

	@InjectMocks
	private SearchServiceImplementation service;
	
	@BeforeEach
	public void setup() {
		airportDTO = new AirportDTO("Chhatrapati Shivaji Intl ", "Mumbai", "India", "BOM", "VABB", 19.0886993408, 72.8678970337, "5.5");
		airport = new Airport("Chhatrapati Shivaji Intl ", "Mumbai", "India", "BOM", "VABB", 19.0886993408, 72.8678970337, "5.5");
		optionalAirport = Optional.ofNullable(airport);
		updatedAirportDTO = new AirportDTO("Chhatrapati Shivaji Intl ", "Mumbai", "India", "BOM", "VABB", 19.0886993408, 72.8678970337, "GMT + 5.5");
		updatedAirport = new Airport("Chhatrapati Shivaji Intl ", "Mumbai", "India", "BOM", "VABB", 19.0886993408, 72.8678970337, "GMT + 5.5");
		airports = new ArrayList<Airport>();
		airportsDTO = new ArrayList<AirportDTO>();
		airports.add(airport);
		airportsDTO.add(airportDTO);
		
		date = new Date();
		prices = new double[3];
		prices[0] = 1499.00;
		prices[1] = 1999.00;
		prices[2] = 2499.00;
		optionalFlight = Optional.ofNullable(flight);
		
		flightDTO = new FlightDTO("ABC123", "5:00 AM", "8:00 AM", "Delta", "delta.png", date, "NAG - Nagpur (Dr. Babasaheb Ambedkar Intl)", "BOM - Mumbai (Chhatrapati Shivaji Intl)", "On-time", prices);
		flight = new Flight("ABC123", "5:00 AM", "8:00 AM", "Delta", "delta.png", date, "NAG - Nagpur (Dr. Babasaheb Ambedkar Intl)", "BOM - Mumbai (Chhatrapati Shivaji Intl)", "On-time", prices);
		optionalFlight = Optional.ofNullable(flight);
		updatedFlight = new Flight("ABC123", "6:00 AM", "9:00 AM", "Delta", "delta.png", date, "NAG - Nagpur (Dr. Babasaheb Ambedkar Intl)", "BOM - Mumbai (Chhatrapati Shivaji Intl)", "On-time", prices);
		updatedFlightDTO = new FlightDTO("ABC123", "6:00 AM", "9:00 AM", "Delta", "delta.png", date, "NAG - Nagpur (Dr. Babasaheb Ambedkar Intl)", "BOM - Mumbai (Chhatrapati Shivaji Intl)", "On-time", prices);
		flights = new ArrayList<Flight>();
		flightsDTO = new ArrayList<FlightDTO>();
		flights.add(flight);
		flightsDTO.add(flightDTO);
		
		result = new ArrayList<String>();
		result.add("BOM - Mumbai (Chhatrapati Shivaji Intl)");
		
		airlines = new ArrayList<String>();
		airlines.add("Delta");
	}

	@Test
	void addAirportTest() {
		when(modelMapper.map(airportDTO, Airport.class)).thenReturn(airport);
		when(airportRepository.save(airport)).thenReturn(airport);
		assertEquals(ResponseEntity.ok().body("Airport Added Successfully!"), service.addAirport(airportDTO));
	}
	
	@Test
	void addAirportsTest() {
		when(modelMapper.map(airportDTO, Airport.class)).thenReturn(airport);
		when(airportRepository.saveAll(airports)).thenReturn(airports);
		assertEquals(ResponseEntity.ok().body("Airports Added Successfully!"), service.addAirports(airportsDTO));
	}
	
	@Test
	void addFlightTest() {
		when(modelMapper.map(flightDTO, Flight.class)).thenReturn(flight);
		when(flightRepository.save(flight)).thenReturn(flight);
		assertEquals(ResponseEntity.ok().body("Flight Added Successfully!"), service.addFlight(flightDTO));
	}
	
	@Test
	void addFlightsTest() {
		when(modelMapper.map(flightDTO, Flight.class)).thenReturn(flight);
		when(flightRepository.saveAll(any())).thenReturn(flights);
		assertEquals(ResponseEntity.ok().body("Flights Added Successfully!"), service.addFlights(flightsDTO));
	}
	
	@Test
	void getCitiesCodeTest() {
		when(airportRepository.findAll()).thenReturn(airports);
		assertEquals(result, service.getCitiesCode());
	}
	
	@Test
	void getAirlinesTest() {
		when(flightRepository.findAll()).thenReturn(flights);
		assertEquals(airlines, service.getAirlines());
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
	void searchFlightBySourceDestinationDateTest() {
		when(flightRepository.findBySourceAndDestinationAndDate("NAG - Nagpur (Dr. Babasaheb Ambedkar Intl)", "BOM - Mumbai (Chhatrapati Shivaji Intl)", date)).thenReturn(flights);
		assertEquals(flights, service.searchFlightBySourceDestinationDate("NAG - Nagpur (Dr. Babasaheb Ambedkar Intl)", "BOM - Mumbai (Chhatrapati Shivaji Intl)", date));
	}
	
	@Test
	void updateAirportTest() throws AirportNotFoundException {
		when(airportRepository.findById("Chhatrapati Shivaji Intl ")).thenReturn(optionalAirport);
		when(airportRepository.save(updatedAirport)).thenReturn(updatedAirport);
		assertEquals(ResponseEntity.ok().body("Airport Updated Successfully!"), service.updateAirport("Chhatrapati Shivaji Intl ", updatedAirportDTO));
	}
	
	@Test
	void updateAirportAirportNotFoundTest() {
		when(airportRepository.findById("Test Airport")).thenReturn(Optional.empty());
		assertThrows(AirportNotFoundException.class, () -> { service.updateAirport("Test Airport", any()); });
	}
	
	@Test
	void updateFlightTest() throws FlightNotFoundException {
		when(flightRepository.findById("ABC123")).thenReturn(optionalFlight);
		when(flightRepository.save(updatedFlight)).thenReturn(updatedFlight);
		assertEquals(ResponseEntity.ok().body("Flight Updated Successfully!"), service.updateFlight("ABC123", updatedFlightDTO));
	}
	
	@Test
	void updateFlightFlightNotFoundTest() {
		when(flightRepository.findById("Test Flight")).thenReturn(Optional.empty());
		assertThrows(FlightNotFoundException.class, () -> { service.updateFlight("Test Flight", any()); });
	}
	
	@Test
	void deleteAirportTest() throws AirportNotFoundException {
		when(airportRepository.findById("Chhatrapati Shivaji Intl ")).thenReturn(optionalAirport);
		doNothing().when(airportRepository).deleteById("Chhatrapati Shivaji Intl ");
		assertEquals(ResponseEntity.ok().body("Airport Deleted Successfully!"), service.deleteAirport("Chhatrapati Shivaji Intl "));
	}
	
	@Test
	void deleteAirportAirportNotFoundTest() {
		when(airportRepository.findById("Test Airport")).thenReturn(Optional.empty());
		assertThrows(AirportNotFoundException.class, () -> { service.deleteAirport("Test Airport"); });
	}
	
	@Test
	void deleteFlightTest() throws FlightNotFoundException {
		when(flightRepository.findById("ABC123")).thenReturn(optionalFlight);
		doNothing().when(flightRepository).deleteById("ABC123");
		assertEquals(ResponseEntity.ok().body("Flight Deleted Successfully!"), service.deleteFlight("ABC123"));
	}
	
	@Test
	void deleteFlightFlightNotFoundTest() {
		when(flightRepository.findById("Test Flight")).thenReturn(Optional.empty());
		assertThrows(FlightNotFoundException.class, () -> { service.deleteFlight("Test Flight"); });
	}

}
