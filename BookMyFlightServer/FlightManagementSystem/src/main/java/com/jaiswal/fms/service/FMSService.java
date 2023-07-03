package com.jaiswal.fms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jaiswal.fms.bean.Contact;
import com.jaiswal.fms.bean.Offer;

public interface FMSService {
	
	public String addOffer(Offer offer);
	public List<Offer> getOffers();
	public ResponseEntity<String> addContact(Contact contact);
	
}
