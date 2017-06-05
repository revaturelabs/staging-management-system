package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.ClientQ;
import com.revature.repositories.ClientQRepo;

@Service
public class ClientQServiceImpl implements ClientQService {
	@Autowired
	ClientQRepo clientQRepo;

	public ClientQServiceImpl(ClientQRepo clientQRepo) {
		super();
		this.clientQRepo = clientQRepo;
	}

	@Override
	public void add(ClientQ clientQ) {
		clientQRepo.saveAndFlush(clientQ);
	}

	@Override
	public Set<ClientQ> getAll() {
		return new HashSet<ClientQ>(clientQRepo.findAll());
	}

	@Override
	public ClientQ findById(long id) {
		return clientQRepo.getOne(id);
	}

	@Override
	public void delete(ClientQ clientQ) {
		clientQRepo.delete(clientQ);
	}

	@Override
	public void update(ClientQ clientQ) {
		clientQRepo.saveAndFlush(clientQ);
	}
}
