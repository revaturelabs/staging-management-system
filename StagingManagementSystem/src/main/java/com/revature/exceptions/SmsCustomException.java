//Parent custom exception class of all custom exceptions within this application.
package com.revature.exceptions;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class SmsCustomException extends HttpStatusCodeException {

	private static final long serialVersionUID = -5013114105102502435L;

	private static final HttpStatus defaultStatus = HttpStatus.I_AM_A_TEAPOT;

	public SmsCustomException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public SmsCustomException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders,
			byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

	public SmsCustomException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public SmsCustomException(HttpStatus statusCode) {
		super(statusCode);
	}

	public SmsCustomException(String statusText) {
		super(defaultStatus, statusText);
	}

}
