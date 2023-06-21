package com.jaiswal.fms.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jaiswal.fms.bean.Airport;
import com.jaiswal.fms.bean.Flight;

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

}
