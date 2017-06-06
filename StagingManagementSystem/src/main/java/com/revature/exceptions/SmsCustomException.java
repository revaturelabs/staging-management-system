//Parent custom exception class of all custom exceptions within this application.
package com.revature.exceptions;

import org.springframework.http.HttpStatus;

import com.revature.markers.SmsHasMessageAndStatus;

public class SmsCustomException extends Throwable implements SmsHasMessageAndStatus {

	private static final long serialVersionUID = -5013114105102502435L;

	private HttpStatus status = HttpStatus.I_AM_A_TEAPOT;

	public SmsCustomException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public SmsCustomException(String message) {
		super(message);
	}

	public SmsCustomException(HttpStatus status) {
		super();
		this.status = status;
	}

	@Override
	public HttpStatus getStatus() {
		return this.status;
	}

}
