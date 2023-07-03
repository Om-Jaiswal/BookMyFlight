package com.jaiswal.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.book.model.FlightCapacity;

@Repository
public interface FlightCapacityRepository extends MongoRepository<FlightCapacity, String> {
	public FlightCapacity findByFlightNumber(String flightNumber);
}
