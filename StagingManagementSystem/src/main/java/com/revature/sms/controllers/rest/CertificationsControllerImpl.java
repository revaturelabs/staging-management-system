package com.revature.sms.controllers.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public Certifications getCert(@PathVariable long cert_id){
		return certService.getById(cert_id);
	}
	
	
@PostMapping
public void add(@RequestBody Certifications certifications, @PathVariable String cert_type){
	certifications.setCert_type(cert_type);
	certService.add(certifications);
}

@GetMapping("/associate/{associateId}")
public Set<Certifications> findByAssociate(@PathVariable long id) {
	return certService.findByAssociate(id);
}
}
