package com.revature.services;

import java.util.Set;

import com.revature.entities.Client;

public interface ClientService {

	Client getClientbyId(long id);

	void add(Client client);

	void addMultiple(Set<Client> clients);

}
