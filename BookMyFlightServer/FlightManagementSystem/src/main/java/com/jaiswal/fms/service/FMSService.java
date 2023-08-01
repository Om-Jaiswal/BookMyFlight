package com.jaiswal.fms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jaiswal.fms.model.Offer;
import com.jaiswal.fms.model.dto.ContactDTO;
import com.jaiswal.fms.model.dto.OfferDTO;

public interface FMSService {
	
	public ResponseEntity<String> addOffer(OfferDTO offer);
	public ResponseEntity<String> updateOffer(OfferDTO offer);
	public Offer getOffer(String title);
	public List<Offer> getOffers();
	public ResponseEntity<String> deleteOffer(String title);
	public ResponseEntity<String> addContact(ContactDTO contact);
	
}
