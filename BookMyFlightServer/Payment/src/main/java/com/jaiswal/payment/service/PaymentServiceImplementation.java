package com.jaiswal.payment.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jaiswal.payment.config.MQConfig;
import com.jaiswal.payment.model.Payment;
import com.jaiswal.payment.model.dto.PaymentDTO;
import com.jaiswal.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImplementation implements PaymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImplementation.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	/**
	 * Method to payment capacity in the database
	 * @param payment - PaymentDTO object which will be added in the database
	 * @return message - confirmation message
	 */
	@RabbitListener(queues = MQConfig.QUEUE)
	public void addPayment(PaymentDTO payment) {
		paymentRepository.save(modelMapper.map(payment, Payment.class));
		logger.warn("Payment Added Successfully!");
	}
	
	/**
	 * Method to update payment
	 * @param payment - PaymentDTO object which will be updated in the database
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> updatePayment(PaymentDTO payment) {
		Optional<Payment> optionalPayment = paymentRepository.findById(payment.getBookingId());
		if (optionalPayment.isPresent()) {
			
			Payment existingPayment = optionalPayment.get();
			
			existingPayment.setAmountPaid(payment.getAmountPaid());
			existingPayment.setPaidBy(payment.getPaidBy());
			existingPayment.setPaymentDate(payment.getPaymentDate());

            paymentRepository.save(existingPayment);
            
            logger.warn("Payment Updated Successfully!");
            return ResponseEntity.ok().body("Payment Updated Successfully!");
        } else {
        	logger.warn("Payment doesn't exist!");
            return ResponseEntity.notFound().build();
        }
	}
	
	/**
	 * Method to get payment
	 * @param bookingId - bookingId by which the payment will be searched
	 * @return payment - payment
	 */
	public Payment getPayment(String bookingId) {
		logger.warn("Payment Fetched!");
		return paymentRepository.findByBookingId(bookingId);
	}
	
	/**
	 * Method to get list of payments
	 * @return payments - list of all payments
	 */
	public List<Payment> getPayments() {
		logger.warn("Payments Fetched!");
		return paymentRepository.findAll();
	}

	/**
	 * Method to delete payment
	 * @param bookingId - bookingId by which the payment will be deleted
	 * @return message - confirmation message
	 */
	public ResponseEntity<String> deletePayment(String bookingId) {
		Optional<Payment> optionalPayment = paymentRepository.findById(bookingId);
		if (optionalPayment.isPresent()) {
			paymentRepository.deleteById(bookingId);
			logger.warn("Payment Deleted Successfully!");
			return ResponseEntity.ok().body("Payment Deleted Successfully!");
		} else {
	        logger.warn("Payment doesn't exist!");
	        return ResponseEntity.notFound().build();
	    }
	}

}
