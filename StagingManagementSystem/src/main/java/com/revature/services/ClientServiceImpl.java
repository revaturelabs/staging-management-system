package com.revature.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Client;
import com.revature.repositories.ClientRepo;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepo clientRepo;

	public ClientServiceImpl(ClientRepo clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}

	@Override
	public void add(Client location) {
		clientRepo.saveAndFlush(location);
	}

	@Override
	public List<Client> getAll() {
		return clientRepo.findAll();
	}


	}

}
