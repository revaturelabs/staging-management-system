package com.revature.sms.controllers.rest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Credential;
//import com.revature.sms.entities.Manager;
import com.revature.sms.security.models.SalesforceUser;
import com.revature.sms.services.CredentialService;

@RestController
@RequestMapping("login")
public class LoginControllerImpl {
	
	@Autowired
	CredentialService credService;
	@Value("${sms.salesforce}")
	private boolean salesforce;
	

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
	
	//NOTE: LM is set in SalesforceAuthorization
	@GetMapping("isManager")
	public ResponseEntity<Boolean> isManager(HttpSession session) {
		
		SalesforceUser manager = (SalesforceUser)session.getAttribute(LM);
		if(manager == null)
			return ResponseEntity.ok(false);
		else
			return ResponseEntity.ok(true);
	}
	
	@GetMapping("user")
	public ResponseEntity<Object> getUser(HttpSession session) {
		
		SalesforceUser manager = (SalesforceUser)session.getAttribute(LM);
		if (manager != null) {
			return ResponseEntity.ok(manager);
		}
		
		Associate associate = (Associate)session.getAttribute(LA);
		if (associate != null) {
			return ResponseEntity.ok(associate);
		}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	//SMS Associate Login only. Manager login handled through Salesforce
	@PostMapping
	public ResponseEntity<Object> smsLogin(@RequestBody Credential creds, HttpSession session, HttpServletResponse resp){
		
		Object obj = credService.login(creds);
		if(obj instanceof Associate){
			session.setAttribute(LA, (Associate)obj);
			return ResponseEntity.ok((Associate)obj);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
}
