package com.jaiswal.fms.exception;

public class AirportNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AirportNotFoundException(String message) {
		super(message);
	}
}
