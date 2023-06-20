package com.jaiswal.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.fms.proxy.SearchFlightProxy;

@RestController
public class FlightController {
	
	@Autowired
	private SearchFlightProxy search;
	
	@GetMapping("cities")
	public List<String> getCities() {
		return search.getCitiesCode();
	}
	
	@GetMapping("flights")
	public List<String> getFlights() {
		return search.searchFlights();
	}
}
