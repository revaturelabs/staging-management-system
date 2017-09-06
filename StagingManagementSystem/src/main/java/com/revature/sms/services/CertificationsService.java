package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Certifications;

public interface CertificationsService {
	
	//associates can add certifications
      public void add(Certifications certification);
      
      public void delete(long cert_id);
      
      public void update(Certifications certifications);
     //get a particular certification by ID
      

     //get all certifications
      public Set<Certifications> getAllCertifications();
//      // get all associates who has particular certificates
      public Set<Certifications> findByAssociate(Long id);
      
      //get all Cert_type 
      //public List<Certifications> getAllCert_type();
     
}
