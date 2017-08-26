package com.revature.sms.controllers.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.CertificationsType;
import com.revature.sms.services.CertificationsTypeService;

@RestController
@RequestMapping("certificationtype")
public class CertificationsTypeControllerImpl {
	@Autowired
	private CertificationsTypeService cts;

	public CertificationsTypeControllerImpl(CertificationsTypeService cts) {
		super();
		this.cts = cts;
	}
	
	
	@GetMapping("/all")
	public Set<CertificationsType> getAllcertType(){
		System.out.println(cts.getAllCertType());
		return cts.getAllCertType();
		
	}
}
