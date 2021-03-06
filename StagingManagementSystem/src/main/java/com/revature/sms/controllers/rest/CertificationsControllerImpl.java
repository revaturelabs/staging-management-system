package com.revature.sms.controllers.rest;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.sms.entities.Certifications;

import com.revature.sms.services.CertificationsService;
import com.revature.sms.services.S3Service;


@RestController
@RequestMapping("certifications")
public class CertificationsControllerImpl {

	@Autowired
	private CertificationsService certService;
	
	@Autowired
	private S3Service s3Service;
	
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
	
     @PutMapping
     public void updateCertifications(@RequestBody Certifications certifications){
    	 System.out.println("i want my associate "+ certifications.getAssociate_id().getName());
    	 certService.update(certifications);
     }
     
     @DeleteMapping("/{cert_id}")
     public void deleteCertifications(@PathVariable long cert_id){
    	 System.out.println("i want my delete ");
    	 certService.delete(cert_id);
     }
     
     @PostMapping(value = "/upload")
  	public void uploadCert(@RequestParam("file") MultipartFile file) throws IOException{
  		System.out.println("############################################# TESTING");
  		System.out.println("TESTING ######################################### ::::::: " + file.getOriginalFilename());
  		
  		s3Service.uploadFile(file);	
  	
  	}

}
