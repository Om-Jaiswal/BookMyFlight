package com.jaiswal.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.repository.AirportRepository;
import com.jaiswal.search.repository.FlightRepository;

@Service
public class SearchServiceImplementation implements SearchService {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImplementation.class);

	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private FlightRepository flightRepository; 
	
	public String addAirport(Airport airport) {
		airportRepository.save(airport);
		logger.warn("Airport Added Successfully!");
		return "Airport Added Successfully!";
	}
	
	public String addAirports(List<Airport> airports) {
		airportRepository.saveAll(airports);
		logger.warn("Airports Added Successfully!");
		return "Airports Added Successfully!";
	}
	
	public String addFlight(Flight flight) {
		flightRepository.save(flight);
		logger.warn("Flight Added Successfully!");
		return "Flight Added Successfully!";
	}
	
	public String addFlights(List<Flight> flights) {
		flightRepository.saveAll(flights);
		logger.warn("Flights Added Successfully!");
		return "Flights Added Successfully!";
	}

	public List<String> getCitiesCode() {
		List<Airport> airports = airportRepository.findAll();
		List<String> codes = airports.stream()
				.filter(city -> !city.getIATA().equals("\\N"))
				.map(airport -> airport.getIATA().trim() + " - " + airport.getCity().trim() + " (" + airport.getName().trim() + ")")
				.collect(Collectors.toList());
		logger.warn("Cities Code Retrieved Successfully!");
		return codes;
	}
	
	public List<Airport> searchAirports() {
		logger.warn("All Aiports Fetched!");
		return airportRepository.findAll();
	}

	public List<Flight> searchFlights() {
		logger.warn("All Flights Fetched!");
		return flightRepository.findAll();
	}
	
}
