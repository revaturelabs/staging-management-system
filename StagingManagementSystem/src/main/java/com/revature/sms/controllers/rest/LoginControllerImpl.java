package com.revature.sms.controllers.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Credential;
import com.revature.sms.entities.Manager;
import com.revature.sms.services.CredentialService;

@RestController
@RequestMapping("login")
public class LoginControllerImpl {
	
	@Autowired
	CredentialService credService;

	private static final String LA = "login_associate";
	private static final String LM = "login_manager";
	
	@GetMapping("isAssociate")
	public ResponseEntity<Boolean> isAssociate(HttpSession session) {
		Associate associate = (Associate)session.getAttribute(LA);
		if(associate == null)
			return ResponseEntity.ok(false);
		else
			return ResponseEntity.ok(true);
	}
	
	@GetMapping("isManager")
	public ResponseEntity<Boolean> isManager(HttpSession session) {
		Manager manager = (Manager)session.getAttribute(LM);
		if(manager == null)
			return ResponseEntity.ok(false);
		else
			return ResponseEntity.ok(true);
	}
	
	@GetMapping("user")
	public ResponseEntity<Object> getUser(HttpSession session) {
		Manager manager = (Manager)session.getAttribute("login_manager");
		Associate associate = (Associate)session.getAttribute("login_associate");
		
		if (manager != null) {
			return ResponseEntity.ok(manager);
		}
		if (associate != null) {
			return ResponseEntity.ok(associate);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	@PostMapping
	public ResponseEntity<Object> dualLogin(@RequestBody Credential creds, HttpSession session){
		Object obj = credService.login(creds);
		if(obj instanceof Associate){
			session.setAttribute(LA, (Associate)obj);
			return ResponseEntity.ok((Associate)obj);
		}else if(obj instanceof Manager){
			session.setAttribute(LM, (Manager)obj);
			return ResponseEntity.ok((Manager)obj);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
