package com.revature.sms.exceptions.badrequests;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends SmsBadRequestException {

    private static final long serialVersionUID = -8571971587270413557L;

    public InvalidFieldException(HttpStatus status) {

        super(status);
    }

    public InvalidFieldException(String message, HttpStatus status) {

        super(message, status);
    }

    public InvalidFieldException(String message) {

        super(message);
    }
}
