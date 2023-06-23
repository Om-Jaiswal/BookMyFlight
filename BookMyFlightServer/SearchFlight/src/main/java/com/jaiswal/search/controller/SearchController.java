package com.jaiswal.search.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
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
	
	@GetMapping("/flights")
	public List<Flight> searchFlightByDate(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		return service.searchFlightByDate(date);
	}
	
	@PutMapping("/update-airport")
	public String updateAirport(@RequestParam("airportName") String airportName, @RequestBody Airport airport) throws AirportNotFoundException {
		return service.updateAirport(airportName, airport);
	}
	
	@PutMapping("/update-flight")
	public String updateFlight(@RequestParam("flightNumber") String flightNumber, @RequestBody Flight flight) throws FlightNotFoundException {
		return service.updateFlight(flightNumber, flight);
	}
	
	@DeleteMapping("/delete-airport")
	public String deleteAirport(@RequestParam("airportName") String airportName) throws AirportNotFoundException {
		return service.deleteAirport(airportName);
	}
	
	@PutMapping("/delete-flight")
	public String deleteFlight(@RequestParam("flightNumber") String flightNumber) throws FlightNotFoundException {
		return service.deleteFlight(flightNumber);
	}
	
}
