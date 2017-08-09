package com.revature.exceptions.badrequests;

import com.revature.exceptions.SmsCustomException;
import org.springframework.http.HttpStatus;

public class SmsBadRequestException extends SmsCustomException {

    private static final long serialVersionUID = -5394611029765710498L;

    private static HttpStatus status = HttpStatus.BAD_REQUEST;

    public SmsBadRequestException(HttpStatus status) {

        super(status);
    }

    public SmsBadRequestException(String message, HttpStatus status) {

        super(message, status);
    }

    public SmsBadRequestException(String message) {

        super(message, status);
    }
}
