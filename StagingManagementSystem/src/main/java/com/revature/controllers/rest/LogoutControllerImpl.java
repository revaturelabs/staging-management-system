package com.revature.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class LogoutControllerImpl {
	
	@GetMapping("/logout")
	public void getLogout() {
		System.out.println("Logout!");
	}
}
