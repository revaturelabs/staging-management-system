package com.revature.exceptions;

public class InvalidFieldException extends SMSCustomException{
	
	private static final long serialVersionUID = -8571971587270413557L;

	public InvalidFieldException() {
		super();
	}

	public InvalidFieldException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidFieldException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidFieldException(String arg0) {
		super(arg0);
	}

	public InvalidFieldException(Throwable arg0) {
		super(arg0);
	}

	
}
