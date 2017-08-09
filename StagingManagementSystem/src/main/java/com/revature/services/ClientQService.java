package com.revature.services;

import com.revature.entities.ClientQuestion;

import java.util.Set;

public interface ClientQService {

    public void add(ClientQuestion location);
    public void delete(ClientQuestion location);
    public void update(ClientQuestion location);
    public ClientQuestion findById(long id);
    public Set<ClientQuestion> getAll();
}
