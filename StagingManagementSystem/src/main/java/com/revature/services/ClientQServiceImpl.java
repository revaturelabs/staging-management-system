package com.revature.services;

import com.revature.entities.ClientQuestion;
import com.revature.repositories.ClientQRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClientQServiceImpl implements ClientQService {

    @Autowired
    ClientQRepo clientQRepo;

    public ClientQServiceImpl(ClientQRepo clientQRepo) {

        super();
        this.clientQRepo = clientQRepo;
    }

    @Override
    public void add(ClientQuestion clientQ) {

        clientQRepo.saveAndFlush(clientQ);
    }

    @Override
    public Set<ClientQuestion> getAll() {

        return new HashSet<ClientQuestion>(clientQRepo.findAll());
    }

    @Override
    public ClientQuestion findById(long id) {

        return clientQRepo.getOne(id);
    }

    @Override
    public void delete(ClientQuestion clientQ) {

        clientQRepo.delete(clientQ);
    }

    @Override
    public void update(ClientQuestion clientQ) {

        clientQRepo.saveAndFlush(clientQ);
    }
}
