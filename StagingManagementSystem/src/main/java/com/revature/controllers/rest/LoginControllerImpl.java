package com.revature.controllers.rest;

import com.revature.entities.Associate;
import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.exceptions.SmsCustomException;
import com.revature.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
public class LoginControllerImpl {
	
	@Autowired
	CredentialService credService;

	@PostMapping("associate/create")
	public void createAssociate(){

	}

	@PostMapping
	public ResponseEntity<Object> dualLogin(@RequestBody Credential creds, HttpSession session){
		Object obj = credService.login(creds);
		if(obj instanceof Associate){
			session.setAttribute("login_associate", (Associate)obj);
			return ResponseEntity.ok((Associate)obj);
		}else if(obj instanceof Manager){
			session.setAttribute("login_manager", (Manager)obj);
			return ResponseEntity.ok((Manager)obj);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
