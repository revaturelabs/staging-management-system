package com.revature.exceptions.badrequests;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.revature.exceptions.SmsCustomException;

public class SmsBadRequest extends SmsCustomException {

	private static final long serialVersionUID = -5394611029765710498L;

	private static final HttpStatus defaultStatus = HttpStatus.BAD_REQUEST;

	public SmsBadRequest(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public SmsBadRequest(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody,
			Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

	public SmsBadRequest(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public SmsBadRequest(HttpStatus statusCode) {
		super(statusCode);
	}

	public SmsBadRequest(String statusText) {
		super(defaultStatus, statusText);
	}
}
