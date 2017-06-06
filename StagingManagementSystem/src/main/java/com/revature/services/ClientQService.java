package com.revature.services;

import java.util.Set;

import com.revature.entities.ClientQuestion;

public interface ClientQService {

	public void add(ClientQuestion location);

	public void delete(ClientQuestion location);

	public void update(ClientQuestion location);

	public ClientQuestion findById(long id);

	public Set<ClientQuestion> getAll();

}
