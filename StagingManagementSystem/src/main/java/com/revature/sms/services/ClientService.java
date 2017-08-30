package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Client;

public interface ClientService {

	Client getClientbyId(long id);

	void add(Client client);

	void addMultiple(Set<Client> clients);

	public void delete(Client client);

	public void update(Client client);

	public Client findById(long id);

	public Set<Client> getAll();
	Set<Client> findPriority();
}
