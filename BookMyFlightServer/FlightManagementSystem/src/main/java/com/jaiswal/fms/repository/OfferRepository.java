package com.jaiswal.fms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.fms.bean.Offer;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

}
