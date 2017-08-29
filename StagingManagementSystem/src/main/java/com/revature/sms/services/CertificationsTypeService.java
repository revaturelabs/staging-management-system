package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.CertificationsType;


public interface CertificationsTypeService {
	public Set<CertificationsType> getAllCertType();
	public void add(CertificationsType certtype);
}
