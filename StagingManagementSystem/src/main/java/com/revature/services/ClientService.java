package com.revature.services;

import com.revature.entities.Client;

import java.util.List;

public interface ClientService {

	Client getClientbyId(long id);

	List<Client> getAll();

	void add(Client client);
	
	
}
