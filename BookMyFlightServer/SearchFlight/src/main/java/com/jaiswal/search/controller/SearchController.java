package com.jaiswal.search.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.model.dto.AirportDTO;
import com.jaiswal.search.model.dto.FlightDTO;
import com.jaiswal.search.model.dto.UploadResponseDTO;
import com.jaiswal.search.service.SearchService;
import com.jaiswal.search.util.SearchUtils;

@RestController
@RequestMapping("/search-flights")
public class SearchController {

	@Autowired
	private SearchService service;
	
	@PostMapping("/upload")
    public ResponseEntity<UploadResponseDTO> uploadImage(@RequestParam("image") MultipartFile image) {
		return SearchUtils.uploadImage(image);
	}
	
	/**
	 * Add airport using service addAirport method
	 * @param airport - AirportDTO object
	 * @return success or failure string
	 */
	@PostMapping("/add-airport")
	public ResponseEntity<String> addAirport(@RequestBody AirportDTO airport) {
		return service.addAirport(airport);
	}
	
	/**
	 * Add airports using service addAirports method
	 * @param airports - list of AirportDTO objects
	 * @return success or failure string
	 */
	@PostMapping("/add-airports")
    public ResponseEntity<String> addAirports(@RequestBody List<AirportDTO> airports) {
        return service.addAirports(airports);
    }
	
	/**
	 * Add flight using service addFlight method
	 * @param flight - FlightDTO object
	 * @return success or failure string
	 */
	@PostMapping("/add-flight")
	public ResponseEntity<String> addFlight(@RequestBody FlightDTO flight) {
		return service.addFlight(flight);
	}
	
	/**
	 * Add flights using service addFlights method
	 * @param flights - list of FlightDTO objects
	 * @return success or failure string
	 */
	@PostMapping("/add-flights")
    public ResponseEntity<String> addFlights(@RequestBody List<FlightDTO> flights) {
        return service.addFlights(flights);
    }
	
	/**
	 * Get cities code using service getCitiesCode method
	 * @return list of strings
	 */
	@GetMapping("/cities-code")
	public List<String> getCitiesCode() {
		return service.getCitiesCode();
	}
	
	/**
	 * Get airlines using service getAirlines method
	 * @return list of strings
	 */
	@GetMapping("/all-airlines")
	public List<String> getAirlines() {
		return service.getAirlines();
	}
	
	/**
	 * Get airports using service getAirports method
	 * @return list of strings
	 */
	@GetMapping("/all-airports")
	public List<Airport> searchAirports() {
		return service.searchAirports();
	}
	
	/**
	 * Get flights using service getFlights method
	 * @return list of strings
	 */
	@GetMapping("/all-flights")
	public List<Flight> searchFlights() {
		return service.searchFlights();
	}
	
	/**
	 * Get flights from source to destination using service searchFlightBySourceDestinationDate method
	 * @param source - string
	 * @param destination - string
	 * @param date - string
	 * @return list of Flight objects
	 */
	@GetMapping("/flights")
	public List<Flight> searchFlightBySourceDestinationDate(@RequestParam("source") String source, @RequestParam("destination") String destination, @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		return service.searchFlightBySourceDestinationDate(source, destination, date);
	}
	
	/**
	 * Update airport using service updateAirport method
	 * @param airportName - string
	 * @param airport - AirportDTO object
	 * @return success or failure string
	 */
	@PutMapping("/update-airport")
	public ResponseEntity<String> updateAirport(@RequestParam("airportName") String airportName, @RequestBody AirportDTO airport) throws AirportNotFoundException {
		return service.updateAirport(airportName, airport);
	}
	
	/**
	 * Update flight using service updateFlight method
	 * @param flightNumber - string
	 * @param flight - FlightDTO object
	 * @return success or failure string
	 */
	@PutMapping("/update-flight")
	public ResponseEntity<String> updateFlight(@RequestParam("flightNumber") String flightNumber, @RequestBody FlightDTO flight) throws FlightNotFoundException {
		return service.updateFlight(flightNumber, flight);
	}
	
	/**
	 * Delete airport using service deleteAirport method
	 * @param airportName - string
	 * @return success or failure string
	 */
	@DeleteMapping("/delete-airport")
	public ResponseEntity<String> deleteAirport(@RequestParam("airportName") String airportName) throws AirportNotFoundException {
		return service.deleteAirport(airportName);
	}
	
	/**
	 * Delete flight using service deleteFlight method
	 * @param flightNumber - string
	 * @return success or failure string
	 */
	@DeleteMapping("/delete-flight")
	public ResponseEntity<String> deleteFlight(@RequestParam("flightNumber") String flightNumber) throws FlightNotFoundException {
		return service.deleteFlight(flightNumber);
	}
	
}
