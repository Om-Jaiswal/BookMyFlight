package com.jaiswal.payment.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="payments")
public class Payment {
	
	@Id
	private String bookingId;
	private double amountPaid;
	private String paidBy;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date paymentDate;

}
