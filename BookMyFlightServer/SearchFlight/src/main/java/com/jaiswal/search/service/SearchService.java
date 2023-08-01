package com.jaiswal.search.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.model.dto.AirportDTO;
import com.jaiswal.search.model.dto.FlightDTO;

public interface SearchService {
	
	public ResponseEntity<String> addAirport(AirportDTO airport);
	public ResponseEntity<String> addAirports(List<AirportDTO> airports);
	public ResponseEntity<String> addFlight(FlightDTO flight);
	public ResponseEntity<String> addFlights(List<FlightDTO> flights);
	public List<String> getCitiesCode();
	public List<String> getAirlines();
	public List<Airport> searchAirports();
	public List<Flight> searchFlights();
	public List<Flight> searchFlightBySourceDestinationDate(String source, String destination, Date date);
	public ResponseEntity<String> updateAirport(String airportName, AirportDTO airport) throws AirportNotFoundException;
	public ResponseEntity<String> updateFlight(String flightNumber, FlightDTO flight) throws FlightNotFoundException;
	public ResponseEntity<String> deleteAirport(String airportName) throws AirportNotFoundException;
	public ResponseEntity<String> deleteFlight(String flightNumber) throws FlightNotFoundException;
	
}
