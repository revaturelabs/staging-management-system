package com.revature.services;

import com.revature.entities.Client;

public interface ClientService {

	Client getClientbyId(long id);
	
	void add(Client client);
	
	
}
