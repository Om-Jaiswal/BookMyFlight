package com.jaiswal.fms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.fms.model.Offer;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
	public Offer findByTitle(String title);
}
