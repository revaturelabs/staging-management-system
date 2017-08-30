package com.revature.sms.markers;

import org.springframework.http.HttpStatus;

public interface SmsHasMessageAndStatus {

    String getMessage();
    HttpStatus getStatus();
}
