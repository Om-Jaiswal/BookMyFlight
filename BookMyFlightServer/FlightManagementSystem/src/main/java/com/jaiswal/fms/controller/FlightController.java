package com.jaiswal.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.fms.bean.Airport;
import com.jaiswal.fms.bean.Flight;
import com.jaiswal.fms.proxy.SearchFlightProxy;

@RestController
public class FlightController {
	
	@Autowired
	private SearchFlightProxy search;
	
	@PostMapping("/add-airport")
	public String addAirport(@RequestBody Airport airport) {
		return search.addAirport(airport);
	}
	
	@PostMapping("/add-airports")
	public String addAirports(@RequestBody List<Airport> airports) {
		return search.addAirports(airports);
	}
	
	@PostMapping("/add-flight")
	public String addFlight(@RequestBody Flight flight) {
		return search.addFlight(flight);
	}
	
	@PostMapping("/add-flights")
	public String addFlights(@RequestBody List<Flight> flights) {
		return search.addFlights(flights);
	}
	
	@GetMapping("/cities-code")
	public List<String> getCitiesCode() {
		return search.getCitiesCode();
	}
	
	@GetMapping("/all-airports")
	public List<Airport> searchAirports() {
		return search.searchAirports();
	}
	
	@GetMapping("/all-flights")
	public List<Flight> searchFlights() {
		return search.searchFlights();
	}
}
