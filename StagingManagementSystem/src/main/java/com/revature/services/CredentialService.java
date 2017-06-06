package com.revature.services;

import java.util.Set;

import com.revature.entities.Credential;

public interface CredentialService {

	public void add(Credential location);

	public void delete(Credential location);

	public void update(Credential location);

	public Credential findById(long id);

	public Set<Credential> getAll();
	
	public Object login(Credential creds);

}
