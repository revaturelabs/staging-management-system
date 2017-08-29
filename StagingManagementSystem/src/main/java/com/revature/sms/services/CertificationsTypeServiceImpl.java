package com.revature.sms.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Certifications;
import com.revature.sms.entities.CertificationsType;
import com.revature.sms.repositories.CertificationsTypeRepo;
@Service
public class CertificationsTypeServiceImpl implements CertificationsTypeService{
	
	@Autowired
	private CertificationsTypeRepo certtyperepo;
	
	public CertificationsTypeServiceImpl(CertificationsTypeRepo certtyperepo) {
		super();
		this.certtyperepo = certtyperepo;
	}
	public Set<CertificationsType> getAllCertType(){
		return new HashSet<>(certtyperepo.findAll());
	}
	
	@Override
	public void add(CertificationsType certtype) {
		certtyperepo.saveAndFlush(certtype);
	}
}
