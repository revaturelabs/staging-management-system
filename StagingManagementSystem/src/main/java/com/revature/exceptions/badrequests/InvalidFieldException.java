package com.revature.exceptions.badrequests;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class InvalidFieldException extends SmsBadRequest {

	private static final long serialVersionUID = -8571971587270413557L;

	public InvalidFieldException(HttpStatus statusCode, String statusText, byte[] responseBody,
			Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public InvalidFieldException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders,
			byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

	public InvalidFieldException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public InvalidFieldException(HttpStatus statusCode) {
		super(statusCode);
	}

	public InvalidFieldException(String statusText) {
		super(statusText);
	}

}
