package com.revature.services;

import java.util.List;

import com.revature.entities.Credential;

public interface CredentialService {

	public void add(Credential location);
	public void delete(Credential location);
	
	public void update(Credential location);
	
	public Credential findById(long id);
	public List<Credential> getAll();

}
