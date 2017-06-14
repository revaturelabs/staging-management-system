package com.revature.services;

import com.revature.entities.Client;
import com.revature.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
    public Set<Client> getAll() {

        return new HashSet<Client>(clientRepo.findAll());
    }

    @Override
    public Client getClientbyId(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addMultiple(Set<Client> clients) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(Client client) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Client client) {
        // TODO Auto-generated method stub
    }

    @Override
    public Client findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }
}

