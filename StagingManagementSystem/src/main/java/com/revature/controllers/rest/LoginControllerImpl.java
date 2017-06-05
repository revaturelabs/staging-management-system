package com.revature.controllers.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Associate;
import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.services.CredentialService;

@RestController
@RequestMapping("login")
public class LoginControllerImpl {
	
	@Autowired
	CredentialService credService;
	
	@PostMapping("associate")
	public Associate postAssociate(@RequestBody Credential creds, HttpServletResponse resp) throws Exception {
		System.out.println("here!");
		Object obj = credService.login(creds);
		if(obj instanceof Manager)
			System.out.println("Is a Manager");
		if(obj instanceof Associate)
			System.out.println("Is a Associate");
		System.out.println(creds.getUsername());
		System.out.println(obj);
		System.out.println(creds.getPassword());
		throw new Exception();
		
//		resp.setStatus(401);
	}
	
	@PostMapping("manager")
	public Manager postManager(@RequestBody Credential creds, HttpServletResponse resp) {
//		System.out.println(creds.getUsername());
//		System.out.println(creds.getPassword());
			
		return new Manager();
		
//		resp.setStatus(401);
	}

}
