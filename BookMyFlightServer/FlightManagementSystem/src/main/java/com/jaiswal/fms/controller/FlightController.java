package com.jaiswal.fms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.fms.bean.Airport;
import com.jaiswal.fms.bean.Flight;
import com.jaiswal.fms.exception.AirportNotFoundException;
import com.jaiswal.fms.exception.FlightNotFoundException;
import com.jaiswal.fms.proxy.SearchFlightProxy;

@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping("/flights")
	public List<Flight> searchFlightByDate(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		return search.searchFlightByDate(date);
	}
	
	@PutMapping("/update-airport")
	public String updateAirport(@RequestParam("airportName") String airportName, @RequestBody Airport airport) throws AirportNotFoundException {
		return search.updateAirport(airportName, airport);
	}
	
	@PutMapping("/search-flights/update-flight")
	public String updateFlight(@RequestParam("flightNumber") String flightNumber, @RequestBody Flight flight) throws FlightNotFoundException {
		return search.updateFlight(flightNumber, flight);
	}
	
	@DeleteMapping("/search-flights/delete-airport")
	public String deleteAirport(@RequestParam("airportName") String airportName) throws AirportNotFoundException {
		return search.deleteAirport(airportName);
	}
	
	@PutMapping("/search-flights/delete-flight")
	public String deleteFlight(@RequestParam("flightNumber") String flightNumber) throws FlightNotFoundException {
		return search.deleteFlight(flightNumber);
	}
}
