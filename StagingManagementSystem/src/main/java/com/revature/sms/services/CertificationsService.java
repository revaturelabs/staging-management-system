package com.revature.sms.services;

import java.util.List;
import java.util.Set;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Certifications;

public interface CertificationsService {
	
	//associates can add certifications
      public void add(Certifications certification);
     //get a particular certification by ID
      public Certifications getById(long cert_id);
     //get all certifications
      public List<Certifications> getAllCertifications();
      // get all associates who has particular certificates
      public Set<Certifications> findByAssociate(Associate associate);
     
}
