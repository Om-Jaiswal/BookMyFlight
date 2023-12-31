package com.jaiswal.search.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.search.model.Airport;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {

}
