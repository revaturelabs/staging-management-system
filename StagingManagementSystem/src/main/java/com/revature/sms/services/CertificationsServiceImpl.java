package com.revature.sms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.revature.sms.entities.Certifications;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.CertificationsRepo;

@Service
public class CertificationsServiceImpl implements CertificationsService {
     @Autowired
  private CertificationsRepo certRepo;
           
     @Autowired
     AssociateRepo associateRepo;
     
     
     
     
	public CertificationsServiceImpl(CertificationsRepo certRepo) {
		super();
		this.certRepo = certRepo;
	}
	

	public CertificationsServiceImpl() {
		super();
		
	}


	@Override
	public void add(Certifications certifications) {
		certRepo.saveAndFlush(certifications);
	}

	@Override
	public Certifications getById(long cert_id) {
		return certRepo.findOne(cert_id);
	}

	@Override
	public List<Certifications> getAllCertifications() {
	List<Certifications> cert= new ArrayList<>();
	    certRepo.findAll().forEach(cert::add);
		return cert;
	}

	@Override
	
	public Set<Certifications> findByAssociate(long id) {
	
		return certRepo.getByAssociate_Id(id);
	}

}
