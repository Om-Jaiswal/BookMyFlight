package com.jaiswal.payment.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {
	
	private String bookingId;
	private double amountPaid;
	private String paidBy;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date paymentDate;

}
