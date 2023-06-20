package com.jaiswal.fms.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jaiswal.fms.bean.Airport;

@FeignClient(name="search-flights", url="localhost:8100")
public interface SearchFlightProxy {
	
	@PostMapping("/search-flights/add-airport")
	public String addAirport(@RequestBody Airport airport);
	
	@PostMapping("/search-flights/add-airports")
    public String addAirports(@RequestBody List<Airport> airports);
	
	@GetMapping("/search-flights/cities-code")
	public List<String> getCitiesCode();
	
	@GetMapping("/all")
	public List<String> searchFlights();

}
