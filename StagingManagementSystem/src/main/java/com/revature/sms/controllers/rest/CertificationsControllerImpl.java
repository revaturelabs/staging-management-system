package com.revature.sms.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Certifications;
import com.revature.sms.services.CertificationsService;

@RestController
@RequestMapping("certifications")
public class CertificationsControllerImpl {

	@Autowired
	private CertificationsService certService;
	
	@GetMapping("/all")
	public List<Certifications> getAllcert(){
		return certService.getAllCertifications();
		
	}
	
}
