package com.revature.test;

import com.revature.config.SmsSettings;

public class Driver {
	public static void main(String[] args) {
		SmsSettings settings = SmsSettings.getInstance();
		System.out.println(settings.get("min_length_password"));
	}
}
