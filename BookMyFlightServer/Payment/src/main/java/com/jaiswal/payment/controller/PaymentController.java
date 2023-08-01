package com.jaiswal.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.payment.model.Payment;
import com.jaiswal.payment.model.dto.PaymentDTO;
import com.jaiswal.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	/**
	 * Add payment using service addPayment method
	 * @param payment - paymentDTO object
	 * @return success or failure string
	 */
	@PostMapping("add-payment")
	public void addPayment(@RequestBody PaymentDTO payment) {
		service.addPayment(payment);
	}
	
	/**
	 * Update payment using service updatePayment method
	 * @param payment - paymentDTO object
	 * @return success or failure string
	 */
	@PutMapping("/update-payment")
	public ResponseEntity<String> updatePayment(@RequestBody PaymentDTO payment) {
		return service.updatePayment(payment);
	}
	
	/**
	 * Get payment using service getPayment method
	 * @param bookingId - string
	 * @return Payment object
	 */
	@GetMapping("/get-payment")
	public Payment getPayment(@RequestParam("bookingId") String bookingId) {
		return service.getPayment(bookingId);
	}
	
	/**
	 * Get list payments using service getPayments method
	 * @return list of Payment objects
	 */
	@GetMapping("/get-payments")
	public List<Payment> getPayments() {
		return service.getPayments();
	}
	
	/**
	 * Delete payment using service deletePayment method
	 * @param bookingId - string
	 * @return success or failure string
	 */
	@DeleteMapping("/delete-payment")
	public ResponseEntity<String> deletePayment(@RequestParam("bookingId") String bookingId) {
		return service.deletePayment(bookingId);
	}

}
