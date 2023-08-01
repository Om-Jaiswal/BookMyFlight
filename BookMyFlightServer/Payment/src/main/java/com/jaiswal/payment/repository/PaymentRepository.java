package com.jaiswal.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jaiswal.payment.model.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
	public Payment findByBookingId(String bookingId);
}
