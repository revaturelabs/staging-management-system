package com.revature.services;

import java.util.HashSet;
import java.util.Set;

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
	public void add(Credential credential) {
		credential = credentialRepo.saveAndFlush(credential);
	}

	@Override
	public Set<Credential> getAll() {
		return new HashSet<Credential>(credentialRepo.findAll());
	}

	@Override
	public Credential findById(long id) {
		return credentialRepo.getOne(id);
	}

	@Override
	public void remove(Credential credential) {
		credentialRepo.delete(credential);
	}

	@Override
	public void update(Credential credential) {
		credential = credentialRepo.saveAndFlush(credential);
	}
}
