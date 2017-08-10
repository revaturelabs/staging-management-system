package com.revature.sms.exceptions.badrequests;

import org.springframework.http.HttpStatus;

public class NullReferenceException extends SmsBadRequestException {

    private static final long serialVersionUID = 8438289918966734573L;

    public NullReferenceException(HttpStatus status) {

        super(status);
    }

    public NullReferenceException(String message, HttpStatus status) {

        super(message, status);
    }

    public NullReferenceException(String message) {

        super(message);
    }
}
