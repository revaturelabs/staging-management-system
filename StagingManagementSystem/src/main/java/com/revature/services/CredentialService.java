package com.revature.services;

import java.util.Set;

import com.revature.entities.Credential;

public interface CredentialService {

	public void add(Credential credential);

	public void remove(Credential credential);

	public void update(Credential credential);

	public Credential findById(long id);

	public Set<Credential> getAll();
	
	public Object login(Credential creds);

}
