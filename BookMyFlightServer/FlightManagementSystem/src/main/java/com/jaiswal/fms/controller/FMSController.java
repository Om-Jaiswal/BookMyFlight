package com.jaiswal.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.fms.bean.Contact;
import com.jaiswal.fms.bean.Offer;
import com.jaiswal.fms.service.FMSService;

@RestController
@RequestMapping("/flight-management-system")
public class FMSController {
	
	@Autowired
	private FMSService service;
	
	@PostMapping("/add-offer")
	public String addOffer(@RequestBody Offer offer) {
		return service.addOffer(offer);
	}
	
	@GetMapping("/get-offers")
	public List<Offer> getOffers() {
		return service.getOffers();
	}
	
	@PostMapping("/add-contact")
	public ResponseEntity<String> addContact(@RequestBody Contact contact) {
		return service.addContact(contact);
	}
	

}
