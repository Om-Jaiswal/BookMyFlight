package com.jaiswal.payment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jaiswal.payment.model.Payment;
import com.jaiswal.payment.model.dto.PaymentDTO;

public interface PaymentService {
	
	public void addPayment(PaymentDTO payment);
	public ResponseEntity<String> updatePayment(PaymentDTO payment);
	public Payment getPayment(String bookingId);
	public List<Payment> getPayments();
	public ResponseEntity<String> deletePayment(String bookingId);

}
