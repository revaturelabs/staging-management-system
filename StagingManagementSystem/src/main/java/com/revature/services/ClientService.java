package com.revature.services;

import java.util.List;

import com.revature.entities.Client;

public interface ClientService {

	public void add(Client client);
	public void delete(Client client);
	
	public void update(Client client);
	
	public Client findById(long id);
	public List<Client> getAll();	
}
