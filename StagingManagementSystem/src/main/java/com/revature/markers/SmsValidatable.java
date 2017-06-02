package com.revature.markers;

import com.revature.exceptions.SMSCustomException;

public interface SmsValidatable {

	void validate() throws SMSCustomException;
}
