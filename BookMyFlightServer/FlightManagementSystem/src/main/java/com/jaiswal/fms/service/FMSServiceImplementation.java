package com.jaiswal.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jaiswal.fms.bean.Contact;
import com.jaiswal.fms.bean.Offer;
import com.jaiswal.fms.repository.ContactRepository;
import com.jaiswal.fms.repository.OfferRepository;

@Service
public class FMSServiceImplementation implements FMSService {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	public String addOffer(Offer offer) {
		offerRepository.save(offer);
		return "Offer Added Successfully!";
	}
	
	public List<Offer> getOffers() {
		return offerRepository.findAll();
	}
	
	public ResponseEntity<String> addContact(Contact contact) {
		contactRepository.save(contact);
		return ResponseEntity.ok().body("{\"message\": \"Message Added Successfully!\"}");
	}

}
