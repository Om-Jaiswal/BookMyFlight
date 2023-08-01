package com.jaiswal.fms.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jaiswal.fms.model.DatabaseSequence;
import com.jaiswal.fms.model.Contact;
import com.jaiswal.fms.model.Offer;
import com.jaiswal.fms.model.dto.ContactDTO;
import com.jaiswal.fms.model.dto.OfferDTO;
import com.jaiswal.fms.repository.ContactRepository;
import com.jaiswal.fms.repository.OfferRepository;

@Service
public class FMSServiceImplementation implements FMSService {
	
	private static final Logger logger = LoggerFactory.getLogger(FMSServiceImplementation.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	/**
	 * Method to generate sequence
	 * @param sequenceName - name of the sequence
	 * @return generated sequence
	 */
	public long generateSequence(String sequenceName) {
		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("sequence",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
	}
	
	/**
	 * Method to add offer in the database
	 * @param offer - OfferDTO object which will be added in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> addOffer(OfferDTO offer) {
		offerRepository.save(modelMapper.map(offer, Offer.class));
		logger.warn("Offer Added Successfully!");
		return ResponseEntity.ok().body("Offer Added Successfully!");
	}
	
	/**
	 * Method to update offer in the database
	 * @param offer - OfferDTO object which will be updated in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> updateOffer(OfferDTO offer) {
		Optional<Offer> optionalOffer = offerRepository.findById(offer.getTitle());
        if (optionalOffer.isPresent()) {
            Offer existingOffer = optionalOffer.get();
            
            existingOffer.setDescription(offer.getDescription());
            existingOffer.setAirline(offer.getAirline());

            offerRepository.save(existingOffer);
            logger.warn("Offer Updated Successfully!");
            return ResponseEntity.ok().body("Offer Updated Successfully!");
        } else {
        	logger.warn("Offer doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
	/**
	 * Method to get offer
	 * @param title - title by which the offer will be searched
	 * @return offer - offer
	 */
	public Offer getOffer(String title) {
		logger.warn("Offer Fetched!");
		return offerRepository.findByTitle(title);
	}
	
	/**
	 * Method to get list of offers
	 * @return offers - list of all offers
	 */
	public List<Offer> getOffers() {
		logger.warn("Offers Fetched!");
		return offerRepository.findAll();
	}
	
	/**
	 * Method to delete offer
	 * @param title - title by which the offer will be deleted
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> deleteOffer(String title) {
		Optional<Offer> optionalOffer = offerRepository.findById(title);
        if (optionalOffer.isPresent()) {
			offerRepository.deleteById(title);
			logger.warn("Offer Deleted Successfully!");
			return ResponseEntity.ok().body("Offer Deleted Successfully!");
        } else {
        	logger.warn("Offer doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
	/**
	 * Method to add contact in the database
	 * @param contact - ContactDTO object which will be added in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> addContact(ContactDTO contact) {
		contact.setContactId(generateSequence(Contact.SEQUENCE_NAME));
		contactRepository.save(modelMapper.map(contact, Contact.class));
		logger.warn("Contact Added Successfully!");
		return ResponseEntity.ok().body("Contact Added Successfully!");
	}

}
