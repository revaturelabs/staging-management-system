package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Credential;
import com.revature.repositories.CredentialRepo;

@Service
public class CredentialServiceImpl implements CredentialService {
	@Autowired
	CredentialRepo credentialRepo;

	public CredentialServiceImpl(CredentialRepo credentialRepo) {
		super();
		this.credentialRepo = credentialRepo;
	}

	@Override
	public void add(Credential location) {
		credentialRepo.saveAndFlush(location);
	}

	@Override
	public List<Credential> getAll() {
		return credentialRepo.findAll();
	}

	@Override
	public Credential findById(long id) {
		return credentialRepo.getOne(id);
	}

	@Override
	public void delete(Credential location) {
		credentialRepo.delete(location);		
	}

	@Override
	public void update(Credential location) {
		credentialRepo.saveAndFlush(location);
	}
}
