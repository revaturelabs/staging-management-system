package com.revature.exceptions;

public class NullReferenceException extends SMSCustomException{

	private static final long serialVersionUID = 8438289918966734573L;

	public NullReferenceException() {
		super();
	}

	public NullReferenceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NullReferenceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NullReferenceException(String arg0) {
		super(arg0);
	}

	public NullReferenceException(Throwable arg0) {
		super(arg0);
	}

	
}
