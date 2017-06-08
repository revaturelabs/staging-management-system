package com.revature.controllers.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("isAssociate")
	public ResponseEntity<Boolean> isAssociate(HttpSession session) {
		System.out.println("isAssociate");
		Associate associate = (Associate)session.getAttribute("login_associate");
		if(associate == null)
			return ResponseEntity.ok(false);
		else
			return ResponseEntity.ok(true);
	}
	
	@GetMapping("isManager")
	public ResponseEntity<Boolean> isManager(HttpSession session) {
		System.out.println("isManager");
		Manager manager = (Manager)session.getAttribute("login_manager");
		if(manager == null)
			return ResponseEntity.ok(false);
		else
			return ResponseEntity.ok(true);
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
