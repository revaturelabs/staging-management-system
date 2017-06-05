package com.revature.exceptions.badrequests;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class NullReferenceException extends SmsBadRequest {

	private static final long serialVersionUID = 8438289918966734573L;

	public NullReferenceException(HttpStatus statusCode, String statusText, byte[] responseBody,
			Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public NullReferenceException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders,
			byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

	public NullReferenceException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public NullReferenceException(HttpStatus statusCode) {
		super(statusCode);
	}

	public NullReferenceException(String statusText) {
		super(statusText);
	}

}
