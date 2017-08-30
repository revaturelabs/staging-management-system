package com.revature.sms.controllers.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	public CertificationsControllerImpl(CertificationsService certService) {
		super();
		this.certService = certService;
	}

	//get a list of all certifications 
	@GetMapping("/all")
	public Set<Certifications> getAllcert(){
		System.out.println(certService.getAllCertifications());
		return certService.getAllCertifications();
		
	}
	
/*	@GetMapping("/all")
	public List<Certifications> getAllCertType(){
		return certService.getAllCert_type();
	}*/
	//get a certification by ID
	@GetMapping("/{id}")
	public Certifications getCert(@PathVariable long cert_id){
		return certService.getById(cert_id);
	}
	
	//add a new certification by type
	@PostMapping("/add/certification")
	public void add(@RequestBody Certifications certifications){
		certService.add(certifications);
	}

	//get associate by id who has a certifications
	@GetMapping("/associate/{associateId}")
	public Set<Certifications> findByAssociate(@PathVariable long associateId) {
		return certService.findByAssociate(associateId);
	}
	
     @PutMapping("/allUpdate")
     public void updateCertifications(@RequestBody Certifications certifications){
    	 certService.update(certifications);
     }
     
     @DeleteMapping
     public void deleteCertifications(@RequestBody Certifications certifications){
    	 certService.delete(certifications);
     }
}
