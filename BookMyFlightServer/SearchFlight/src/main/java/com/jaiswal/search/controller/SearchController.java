package com.jaiswal.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.service.SearchService;

@RestController
@RequestMapping("/search-flights")
public class SearchController {

	@Autowired
	private SearchService service;
	
	@PostMapping("/add-airport")
	public String addAirport(@RequestBody Airport airport) {
		return service.addAirport(airport);
	}
	
	@PostMapping("/add-airports")
    public String addAirports(@RequestBody List<Airport> airports) {
        return service.addAirports(airports);
    }
	
	@PostMapping("/add-flight")
	public String addFlight(@RequestBody Flight flight) {
		return service.addFlight(flight);
	}
	
	@PostMapping("/add-flights")
    public String addFlights(@RequestBody List<Flight> flights) {
        return service.addFlights(flights);
    }
	
	@GetMapping("/cities-code")
	public List<String> getCitiesCode() {
		return service.getCitiesCode();
	}
	
	@GetMapping("/all-airports")
	public List<Airport> searchAirports() {
		return service.searchAirports();
	}
	
	@GetMapping("/all-flights")
	public List<Flight> searchFlights() {
		return service.searchFlights();
	}
	
}
