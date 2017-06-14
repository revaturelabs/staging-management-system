package com.revature.markers;

import org.springframework.http.HttpStatus;

public interface SmsHasMessageAndStatus {

    String getMessage();
    HttpStatus getStatus();
}
