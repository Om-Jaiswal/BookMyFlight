package com.jaiswal.book.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.book.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, Long> {
	public Booking findByBookingId(long bookingId);
	public List<Booking> findByPaidBy(String paidBy);
}
