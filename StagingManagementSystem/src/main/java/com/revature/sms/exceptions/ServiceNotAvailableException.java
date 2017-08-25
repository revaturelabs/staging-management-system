package com.revature.sms.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/* Shamelessly stolen from com.revature.caliber.exceptions
 * Originally written by Patrick Walsh or his team.
 */
@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE, reason="The system is currently unavailable. Please contact technology support.")
public class ServiceNotAvailableException extends RuntimeException {
	private static final long serialVersionUID = 4073491812380749518L;
}
