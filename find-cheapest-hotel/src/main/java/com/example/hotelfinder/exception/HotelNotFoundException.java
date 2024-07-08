package com.example.hotelfinder.exception;

public class HotelNotFoundException extends RuntimeException {

	public HotelNotFoundException(String message) {
		super(message);
	}

	public HotelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
