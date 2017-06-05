package com.revature.controllers.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Credential;

@RestController
@RequestMapping("login")
public class LoginControllerImpl {
	
	@PostMapping
	public void postLogin(@RequestBody Credential creds, HttpServletResponse resp) {
		System.out.println(creds.getUsername());
		System.out.println(creds.getPassword());
		
//		resp.setStatus(401);
	}

}
