package com.revature.sms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Certifications;
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
	public Set<Certifications> getAllCertifications() {
		return new HashSet<>(certRepo.findAll());
	}

	@Override

	public Set<Certifications> findByAssociate(Long id) {
		return certRepo.getByAssociate_Id(id);
	}

	@Override
	public void delete(long cert_id) {
	        certRepo.delete(cert_id);
		
	}

	@Override
	public void update(Certifications certifications) {
	      certRepo.saveAndFlush(certifications);
		
	}

	
/*	@Override
	public List<Certifications> getAllCert_type() {
		List<Certifications> certs = certRepo.getByCert_type();
		return certs;
	
		
	}*/

}
