package com.revature.exceptions;

public class NonUniqueException extends SMSCustomException{

	private static final long serialVersionUID = -5987523201810602207L;

	public NonUniqueException() {
		super();
	}

	public NonUniqueException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NonUniqueException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NonUniqueException(String arg0) {
		super(arg0);
	}

	public NonUniqueException(Throwable arg0) {
		super(arg0);
	}
	
}
