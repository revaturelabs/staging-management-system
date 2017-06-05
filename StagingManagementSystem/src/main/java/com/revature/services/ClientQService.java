package com.revature.services;

import java.util.Set;

import com.revature.entities.ClientQ;

public interface ClientQService {

	public void add(ClientQ location);

	public void delete(ClientQ location);

	public void update(ClientQ location);

	public ClientQ findById(long id);

	public Set<ClientQ> getAll();

}
