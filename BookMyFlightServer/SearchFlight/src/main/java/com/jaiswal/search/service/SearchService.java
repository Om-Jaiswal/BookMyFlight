package com.jaiswal.search.service;

import java.util.Date;
import java.util.List;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;

public interface SearchService {
	
	public String addAirport(Airport airport);
	public String addAirports(List<Airport> airports);
	public String addFlight(Flight flight);
	public String addFlights(List<Flight> flights);
	public List<String> getCitiesCode();
	public List<Airport> searchAirports();
	public List<Flight> searchFlights();
	public List<Flight> searchFlightBySourceDestinationDate(String source, String destination, Date date);
	public String updateAirport(String airportName, Airport airport) throws AirportNotFoundException;
	public String updateFlight(String flightNumber, Flight flight) throws FlightNotFoundException;
	public String deleteAirport(String airportName) throws AirportNotFoundException;
	public String deleteFlight(String flightNumber) throws FlightNotFoundException;
	
}
