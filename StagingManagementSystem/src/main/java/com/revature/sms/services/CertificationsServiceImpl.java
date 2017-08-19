package com.revature.sms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Certifications;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.CertificationsRepo;

@Service
public class CertificationsServiceImpl implements CertificationsService {


	@Autowired
 CertificationsRepo certRepo;

	
	public CertificationsServiceImpl(CertificationsRepo certRepo) {
		super();
		this.certRepo = certRepo;
	
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

	public Set<Certifications> findByAssociate(Associate associate) {
		return certRepo.getByAssociate_Id(associate);
	}

}
