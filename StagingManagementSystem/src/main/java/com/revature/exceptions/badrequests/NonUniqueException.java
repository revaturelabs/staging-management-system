package com.revature.exceptions.badrequests;

import org.springframework.http.HttpStatus;

public class NonUniqueException extends SmsBadRequestException {

	private static final long serialVersionUID = -5987523201810602207L;

	public NonUniqueException(HttpStatus status) {
		super(status);
	}

	public NonUniqueException(String message, HttpStatus status) {
		super(message, status);
	}

	public NonUniqueException(String message) {
		super(message);
	}

}
