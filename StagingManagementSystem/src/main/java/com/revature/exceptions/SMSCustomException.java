package com.revature.exceptions;

public class SMSCustomException extends Exception{
	
	private static final long serialVersionUID = -7130611205445787160L;

	public SMSCustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SMSCustomException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SMSCustomException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SMSCustomException(String arg0) {
		super(arg0);
	}

	public SMSCustomException(Throwable arg0) {
		super(arg0);
	}

	
}
