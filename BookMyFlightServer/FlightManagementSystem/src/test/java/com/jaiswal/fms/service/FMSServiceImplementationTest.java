package com.jaiswal.fms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;

import com.jaiswal.fms.model.Contact;
import com.jaiswal.fms.model.DatabaseSequence;
import com.jaiswal.fms.model.Offer;
import com.jaiswal.fms.model.dto.ContactDTO;
import com.jaiswal.fms.model.dto.OfferDTO;
import com.jaiswal.fms.repository.ContactRepository;
import com.jaiswal.fms.repository.OfferRepository;

@ExtendWith(MockitoExtension.class)
class FMSServiceImplementationTest {
	
	private OfferDTO offerDTO;
	private OfferDTO invalidOfferDTO;
	private Offer offer;
	private Optional<Offer> optionalOffer;
	private Offer updatedOffer;
	private List<Offer> offers;
	private ContactDTO contactDTO;
	private DatabaseSequence databaseSequence;
	private Contact contact;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private MongoOperations mongoOperations;
	
	@Mock
	private OfferRepository offerRepository;
	
	@Mock
	private ContactRepository contactRepository;
	
	@InjectMocks
	private FMSServiceImplementation service;
	
	@BeforeEach
	public void setup() {
		offerDTO = new OfferDTO("Test", "Test Description", "Test Airline");
	    invalidOfferDTO = new OfferDTO("Test", "Test Description", "Test Airline");
	    offer = new Offer("FTest", "Test Description", "Test Airline");
	    optionalOffer = Optional.of(offer);
	    updatedOffer = new Offer("Test", "New Test Description", "Test Airline");
	    offers = new ArrayList<>();
		offers.add(offer);
		contactDTO = new ContactDTO(1L, "Test", "test@gmail.com", "Test Message");
		databaseSequence = new DatabaseSequence();
	    databaseSequence.setSequence(1); 
		contact = new Contact(1L, "Test", "test@gmail.com", "Test Message");
	}

	@Test
	void addOfferTest() {
		when(modelMapper.map(offerDTO, Offer.class)).thenReturn(offer);
		when(offerRepository.save(offer)).thenReturn(offer);
		assertEquals(ResponseEntity.ok().body("Offer Added Successfully!"), service.addOffer(offerDTO));
	}
	
	@Test
	void updateOfferTest() {
		when(offerRepository.findById("Test")).thenReturn(optionalOffer);
		doReturn(updatedOffer).when(offerRepository).save(any(Offer.class));
		assertEquals(ResponseEntity.ok().body("Offer Updated Successfully!"), service.updateOffer(offerDTO));
	}
	
	@Test
	void updateOfferOfferNotFoundTest() {
		when(offerRepository.findById(any())).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.updateOffer(invalidOfferDTO));
	}
	
	@Test
	void getOfferTest() {
		when(offerRepository.findByTitle("Test")).thenReturn(offer);
		assertEquals(offer, service.getOffer("Test"));
	}
	
	@Test
	void getOffersTest() {
		when(offerRepository.findAll()).thenReturn(offers);
		assertEquals(offers, service.getOffers());
	}
	
	@Test
	void deleteOfferTest() {
		when(offerRepository.findById("Test")).thenReturn(optionalOffer);
		doNothing().when(offerRepository).deleteById("Test");
		assertEquals(ResponseEntity.ok().body("Offer Deleted Successfully!"), service.deleteOffer("Test"));
	}
	
	@Test
	void deleteOfferOfferNotFoundTest() {
		when(offerRepository.findById(any())).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.deleteOffer("Test"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void addContactTest() {
		when(modelMapper.map(contactDTO, Contact.class)).thenReturn(contact);
	    when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class), any(Class.class))).thenReturn(databaseSequence);
		when(contactRepository.save(contact)).thenReturn(contact);
		assertEquals(ResponseEntity.ok().body("Contact Added Successfully!"), service.addContact(contactDTO));
	}
}
