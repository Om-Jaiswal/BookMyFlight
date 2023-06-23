package com.jaiswal.search.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaiswal.search.exception.AirportNotFoundException;
import com.jaiswal.search.exception.FlightNotFoundException;
import com.jaiswal.search.model.Airport;
import com.jaiswal.search.model.Flight;
import com.jaiswal.search.repository.AirportRepository;
import com.jaiswal.search.repository.FlightRepository;

@Service
public class SearchServiceImplementation implements SearchService {

	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImplementation.class);

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private FlightRepository flightRepository;
	
	/**
	 * Method to add an airport in the database
	 * @param airport - airport object which will be added in the database
	 * @return message - confirmation message
	 */
	public String addAirport(Airport airport) {
		airportRepository.save(airport);
		logger.warn("Airport Added Successfully!");
		return "Airport Added Successfully!";
	}
	
	/**
	 * Method to add multiple airports in the database
	 * @param airports - list of airport objects which will be added in the database
	 * @return message - confirmation message
	 */
	public String addAirports(List<Airport> airports) {
		airportRepository.saveAll(airports);
		logger.warn("Airports Added Successfully!");
		return "Airports Added Successfully!";
	}

	/**
	 * Method to add a flight in the database
	 * @param flight - flight object which will be added in the database
	 * @return message - confirmation message
	 */
	public String addFlight(Flight flight) {
		flightRepository.save(flight);
		logger.warn("Flight Added Successfully!");
		return "Flight Added Successfully!";
	}

	/**
	 * Method to add multiple flights in the database
	 * @param flights - list of flight objects which will be added in the database
	 * @return message - confirmation message
	 */
	public String addFlights(List<Flight> flights) {
		flightRepository.saveAll(flights);
		logger.warn("Flights Added Successfully!");
		return "Flights Added Successfully!";
	}

	/**
	 * Method to get list of cities code
	 * @return codes - list of cities code in a formatted way [IATACode - CityName (AirportName)]
	 */
	public List<String> getCitiesCode() {
		List<Airport> airports = airportRepository.findAll();
		List<String> codes = airports
				.stream().filter(city -> !city.getIATA().equals("\\N")).map(airport -> airport.getIATA().trim() + " - "
						+ airport.getCity().trim() + " (" + airport.getName().trim() + ")")
				.toList();
		logger.warn("Cities Code Retrieved Successfully!");
		return codes;
	}
	
	/**
	 * Method to get the list of all airports in the database
	 * @return airports - list of all airports available in the database
	 */
	public List<Airport> searchAirports() {
		logger.warn("All Aiports Fetched!");
		return airportRepository.findAll();
	}

	/**
	 * Method to get the list of all flights in the database
	 * @return flights - list of all flights available in the database
	 */
	public List<Flight> searchFlights() {
		logger.warn("All Flights Fetched!");
		return flightRepository.findAll();
	}

	/**
	 * Method to get the list of all flights by date in the database
	 * @return flights - list of all flights available in the database filter by date
	 */
	public List<Flight> searchFlightByDate(Date date) {
		logger.warn("Flights By Date Fetched!");
		return flightRepository.findByDate(date);
	}

	/**
	 * Method to update an airport
	 * @param airportName - name of the airport
	 * @param airport - airport object will be updated
	 * @return message - confirmation message
	 */
	public String updateAirport(String airportName, Airport airport) throws AirportNotFoundException {
		Optional<Airport> airportOpt = airportRepository.findById(airportName);
		if (airportOpt.isPresent()) {
			Airport updatedAirport = airportOpt.get();

			updatedAirport.setCity(airport.getCity());
			updatedAirport.setCountry(airport.getCountry());
			updatedAirport.setIATA(airport.getIATA());
			updatedAirport.setICAO(airport.getICAO());
			updatedAirport.setLat(airport.getLat());
			updatedAirport.setLon(airport.getLon());
			updatedAirport.setTimezone(airport.getTimezone());

			airportRepository.save(updatedAirport);

			logger.warn("Airport Updated Successfully!");
			return "Airport Updated Successfully!";
		}

		logger.warn("UPDATE FAIL: {} airport does not exist!", airportName);
		throw new AirportNotFoundException("UPDATE FAIL: " + airportName + " airport does not exist!");
	}

	/**
	 * Method to update a flight
	 * @param flightName - name of the flight
	 * @param flight - flight object will be updated
	 * @return message - confirmation message
	 */
	public String updateFlight(String flightNumber, Flight flight) throws FlightNotFoundException {
		Optional<Flight> flightOpt = flightRepository.findById(flightNumber);
		if (flightOpt.isPresent()) {
			Flight updatedFlight = flightOpt.get();

			updatedFlight.setDepartureTime(flight.getDepartureTime());
			updatedFlight.setArrivalTime(flight.getArrivalTime());
			updatedFlight.setAirline(flight.getAirline());
			updatedFlight.setDate(flight.getDate());
			updatedFlight.setStatus(flight.getStatus());
			updatedFlight.setPrice(flight.getPrice());

			flightRepository.save(updatedFlight);

			logger.warn("Flight Updated Successfully!");
			return "Flight Updated Successfully!";
		}

		logger.warn("UPDATE FAIL: Flight number {} does not exist!", flightNumber);
		throw new FlightNotFoundException("UPDATE FAIL: Flight number " + flightNumber + " does not exist!");
	}

	/**
	 * Method to delete an airport
	 * @param airportName - name of the airport
	 * @return message - confirmation message
	 */
	public String deleteAirport(String airportName) throws AirportNotFoundException {
		Optional<Airport> airportOpt = airportRepository.findById(airportName);
		if (airportOpt.isPresent()) {

			airportRepository.deleteById(airportName);

			logger.warn("Airport Deleted Successfully!");
			return "Airport Deleted Successfully!";
		}

		logger.warn("DELETE FAIL: {} airport does not exist!", airportName);
		throw new AirportNotFoundException("DELETE FAIL: " + airportName + " airport does not exist!");
	}

	/**
	 * Method to delete a flight
	 * @param flightNumber - flight number
	 * @return message - confirmation message
	 */
	public String deleteFlight(String flightNumber) throws FlightNotFoundException {
		Optional<Flight> flightOpt = flightRepository.findById(flightNumber);
		if (flightOpt.isPresent()) {

			flightRepository.deleteById(flightNumber);

			logger.warn("Flight Deleted Successfully!");
			return "Flight Deleted Successfully!";
		}

		logger.warn("DELETE FAIL: Flight number {} does not exist!", flightNumber);
		throw new FlightNotFoundException("DELETE FAIL: Flight number " + flightNumber + " does not exist!");
	}

}
