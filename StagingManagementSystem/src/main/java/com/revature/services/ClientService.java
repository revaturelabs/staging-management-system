package com.revature.services;

import java.util.Set;

import com.revature.entities.Client;

import java.util.List;

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
