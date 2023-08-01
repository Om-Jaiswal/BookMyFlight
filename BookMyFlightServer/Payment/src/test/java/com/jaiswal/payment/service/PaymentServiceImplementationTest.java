package com.jaiswal.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.jaiswal.payment.model.Payment;
import com.jaiswal.payment.model.dto.PaymentDTO;
import com.jaiswal.payment.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplementationTest {
	
	private PaymentDTO paymentDTO;
	private PaymentDTO invalidPaymentDTO;
	private Payment payment;
	private Optional<Payment> optionalPayment;
	private Payment updatedPayment;
	private List<Payment> payments;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@InjectMocks
	private PaymentServiceImplementation service;
	
	@BeforeEach
	public void setup() {
		paymentDTO = new PaymentDTO("1", 314790, "johndoe", new Date());
		invalidPaymentDTO = new PaymentDTO("0", 314790, "johndoe", new Date());
		payment = new Payment("1", 314790, "johndoe", new Date());
		optionalPayment = Optional.of(payment);
		updatedPayment = new Payment("1", 314790, "johndoe", new Date());
		payments = new ArrayList<>();
		payments.add(payment);
	}

	@Test
	void addPaymentTest() {
		when(modelMapper.map(paymentDTO, Payment.class)).thenReturn(payment);
		when(paymentRepository.save(payment)).thenReturn(payment);
		service.addPayment(paymentDTO);
		verify(paymentRepository).save(payment);
	}
	
	@Test
	void updatePaymentTest() {
		when(paymentRepository.findById("1")).thenReturn(optionalPayment);
		doReturn(updatedPayment).when(paymentRepository).save(any(Payment.class));
		assertEquals(ResponseEntity.ok().body("Payment Updated Successfully!"), service.updatePayment(paymentDTO));
	}
	
	@Test
	void updatePaymentPaymentNotFoundTest() {
		when(paymentRepository.findById("0")).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.updatePayment(invalidPaymentDTO));
	}
	
	@Test
	void getPaymentTest() {
		when(paymentRepository.findByBookingId("1")).thenReturn(payment);
		assertEquals(payment, service.getPayment("1"));
	}
	
	@Test
	void getPaymentsTest() {
		when(paymentRepository.findAll()).thenReturn(payments);
		assertEquals(payments, service.getPayments());
	}
	
	@Test
	void deletePaymentTest() {
		when(paymentRepository.findById("1")).thenReturn(optionalPayment);
		doNothing().when(paymentRepository).deleteById("1");
		assertEquals(ResponseEntity.ok().body("Payment Deleted Successfully!"), service.deletePayment("1"));
	}
	
	@Test
	void deletePaymentPaymentNotFoundTest() {
		when(paymentRepository.findById("0")).thenReturn(Optional.empty());
		assertEquals(ResponseEntity.notFound().build(), service.deletePayment("0"));
	}

}
