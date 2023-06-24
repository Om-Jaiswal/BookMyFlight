package com.jaiswal.search.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.search.model.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
	List<Flight> findBySourceAndDestinationAndDate(String source, String destination, Date date);
}
