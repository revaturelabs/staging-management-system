package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.ClientQuestion;

public interface ClientQService {

    public void add(ClientQuestion location);
    public void delete(ClientQuestion location);
    public void update(ClientQuestion location);
    public ClientQuestion findById(long id);
    public Set<ClientQuestion> getAll();
}
