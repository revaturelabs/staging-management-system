package com.revature.controllers.rest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.exceptions.SmsCustomException;
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

	@PostMapping("associate/create")
	public void createAssociate(){

	}

	@PostMapping("associate")
	public Associate postAssociate(@RequestBody Credential creds, HttpSession session, HttpServletResponse resp) throws Exception {
		System.out.println("Logging in!");
		Object obj = credService.login(creds);
		if(obj instanceof Associate){
			session.setAttribute("login_associate", obj);
			return (Associate)obj;
		}
		throw new SmsCustomException("Invalid login attempt");
		
//		resp.setStatus(401);
	}
	
	@PostMapping("manager")
	public Manager postManager(@RequestBody Credential creds,HttpSession session, HttpServletResponse resp) {
		Object obj = credService.login(creds);
		if(obj instanceof Manager){
			session.setAttribute("login_manager", obj);
			return (Manager)obj;
		}
		throw new SmsCustomException("Invalid login attempt");

	}

}
