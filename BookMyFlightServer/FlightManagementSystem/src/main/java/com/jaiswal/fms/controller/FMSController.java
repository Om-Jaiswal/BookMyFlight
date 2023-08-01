package com.jaiswal.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.fms.model.Offer;
import com.jaiswal.fms.model.dto.ContactDTO;
import com.jaiswal.fms.model.dto.OfferDTO;
import com.jaiswal.fms.proxy.SearchFlightProxy;
import com.jaiswal.fms.service.FMSService;

@RestController
@RequestMapping("/flight-management-system")
public class FMSController {
	
	@Autowired
	private SearchFlightProxy search;
	
	@Autowired
	private FMSService service;
	
	/**
	 * Get list airlines using proxy getAirlines method
	 * @return list of airlines
	 */
	@GetMapping("/all-airlines")
	public List<String> getAirlines() {
		return search.getAirlines();
	}
	
	/**
	 * Add offer using service addOffer method
	 * @param offer - OfferDTO object
	 * @return success or failure string
	 */
	@PostMapping("/add-offer")
	public ResponseEntity<String> addOffer(@RequestBody OfferDTO offer) {
		return service.addOffer(offer);
	}
	
	/**
	 * Update offer using service updateOffer method
	 * @param offer - OfferDTO object
	 * @return success or failure string
	 */
	@PutMapping("/update-offer")
	public ResponseEntity<String> updateOffer(@RequestBody OfferDTO offer) {
		return service.updateOffer(offer);
	}
	
	/**
	 * Get offer using service getOffer method
	 * @param title - string
	 * @return Offer object
	 */
	@GetMapping("/get-offer")
	public Offer getOffer(@RequestParam("title") String title) {
		return service.getOffer(title);
	}
	
	/**
	 * Get list offers using service getOffers method
	 * @return list of Offer objects
	 */
	@GetMapping("/get-offers")
	public List<Offer> getOffers() {
		return service.getOffers();
	}
	
	/**
	 * Delete offer using service deleteOffer method
	 * @param title - string
	 * @return success or failure string
	 */
	@DeleteMapping("/delete-offer")
	public ResponseEntity<String> deleteOffer(@RequestParam("title") String title) {
		return service.deleteOffer(title);
	}
	
	/**
	 * Add contact using service addContact method
	 * @param contact - ContactDTO object
	 * @return success or failure string
	 */
	@PostMapping("/add-contact")
	public ResponseEntity<String> addContact(@RequestBody ContactDTO contact) {
		return service.addContact(contact);
	}

}
