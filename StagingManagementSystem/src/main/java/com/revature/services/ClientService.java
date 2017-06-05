package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.entities.Client;

public interface ClientService {

	Client getClientbyId(long id);

	void add(Client client);

	void addMultiple(Set<Client> clients);

	public void delete(Client client);

	public void update(Client client);

	public Client findById(long id);

	public List<Client> getAll();
}
