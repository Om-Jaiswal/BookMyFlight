package com.jaiswal.search.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.search.model.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {

}
