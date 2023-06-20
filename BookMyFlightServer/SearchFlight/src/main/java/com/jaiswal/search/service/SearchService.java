package com.jaiswal.search.service;


import java.util.List;

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
}
