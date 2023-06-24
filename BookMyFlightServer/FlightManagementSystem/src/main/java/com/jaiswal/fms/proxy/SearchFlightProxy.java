package com.jaiswal.fms.proxy;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaiswal.fms.bean.Airport;
import com.jaiswal.fms.bean.Flight;
import com.jaiswal.fms.exception.AirportNotFoundException;
import com.jaiswal.fms.exception.FlightNotFoundException;

@FeignClient(name="search-flights", url="localhost:8100")
public interface SearchFlightProxy {
	
	@PostMapping("/search-flights/add-airport")
	public String addAirport(@RequestBody Airport airport);
	
	@PostMapping("/search-flights/add-airports")
    public String addAirports(@RequestBody List<Airport> airports);
	
	@PostMapping("/search-flights/add-flight")
	public String addFlight(@RequestBody Flight flight);
	
	@PostMapping("/search-flights/add-flights")
    public String addFlights(@RequestBody List<Flight> flights);
	
	@GetMapping("/search-flights/cities-code")
	public List<String> getCitiesCode();
	
	@GetMapping("/search-flights/all-airports")
	public List<Airport> searchAirports();
	
	@GetMapping("/search-flights/all-flights")
	public List<Flight> searchFlights();
	
	@GetMapping("/search-flights/flights")
	public List<Flight> searchFlightBySourceDestinationDate(@RequestParam("source") String source, @RequestParam("destination") String destination, @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date);
	
	@PutMapping("/search-flights/update-airport")
	public String updateAirport(@RequestParam("airportName") String airportName, @RequestBody Airport airport) throws AirportNotFoundException;
	
	@PutMapping("/search-flights/update-flight")
	public String updateFlight(@RequestParam("flightNumber") String flightNumber, @RequestBody Flight flight) throws FlightNotFoundException;
	
	@DeleteMapping("/search-flights/delete-airport")
	public String deleteAirport(@RequestParam("airportName") String airportName) throws AirportNotFoundException;
	
	@PutMapping("/search-flights/delete-flight")
	public String deleteFlight(@RequestParam("flightNumber") String flightNumber) throws FlightNotFoundException;

}
