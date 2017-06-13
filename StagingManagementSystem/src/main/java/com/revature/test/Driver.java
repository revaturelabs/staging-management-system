package com.revature.test;

import com.revature.entities.Credential;

public class Driver {
	
	public static void main(String[] args) {
		Credential one = new Credential(1l,"Paul","password");
		Credential two = new Credential(5l,"Paul","password");
		
		System.out.println(one.hashCode());
		System.out.println(two.hashCode());
		System.out.println(one == two);
		System.out.println(one.equals(two));
	}
}
