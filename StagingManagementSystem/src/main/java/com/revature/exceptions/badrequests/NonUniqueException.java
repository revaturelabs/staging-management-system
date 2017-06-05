package com.revature.exceptions.badrequests;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class NonUniqueException extends SmsBadRequest {

	private static final long serialVersionUID = -5987523201810602207L;

	public NonUniqueException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public NonUniqueException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders,
			byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

	public NonUniqueException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public NonUniqueException(HttpStatus statusCode) {
		super(statusCode);
	}

	public NonUniqueException(String statusText) {
		super(statusText);
	}

}
