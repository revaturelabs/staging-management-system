package com.revature.services;

import java.util.List;

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
	public void add(Client location) {
		clientRepo.saveAndFlush(location);
	}

	@Override
	public List<Client> getAll() {
		return clientRepo.findAll();
	}

	@Override
	public Client findById(long id) {
		return clientRepo.getOne(id);
	}

	@Override
	public void delete(Client location) {
		clientRepo.delete(location);		
	}

	@Override
	public void update(Client location) {
		clientRepo.saveAndFlush(location);
	}

}
