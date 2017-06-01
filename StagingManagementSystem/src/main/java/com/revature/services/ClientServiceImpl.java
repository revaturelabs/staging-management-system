package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Client;
import com.revature.repositories.ClientRepo;

@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	ClientRepo clientRepo;
	
	public ClientServiceImpl(ClientRepo clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}

	@Override
	public Client getClientbyId(long id) {
		return clientRepo.findOne(id);
	}

	@Override
	public void add(Client client) {
		clientRepo.saveAndFlush(client);
		
	}

}
