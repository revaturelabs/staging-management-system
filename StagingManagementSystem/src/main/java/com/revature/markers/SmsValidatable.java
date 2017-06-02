package com.revature.markers;

import com.revature.exceptions.SmsCustomException;

public interface SmsValidatable {

	void validate() throws SmsCustomException;
}
