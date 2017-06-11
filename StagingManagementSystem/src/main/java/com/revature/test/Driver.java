package com.revature.test;

import com.revature.entities.Manager;
import com.revature.exceptions.SmsCustomException;

public class Driver {
	
	public static void main(String[] args) {
		Manager one = new Manager();
		one.setName("Bob");
		try {
			one.validate();
		} catch (SmsCustomException e) {
			e.printStackTrace();
		}
	}
}
