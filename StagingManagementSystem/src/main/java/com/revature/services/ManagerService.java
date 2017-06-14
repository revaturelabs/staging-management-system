package com.revature.services;

import com.revature.entities.Credential;
import com.revature.entities.Manager;
import com.revature.exceptions.SmsCustomException;

import java.util.Set;

public interface ManagerService {

    // c
    Manager add(Manager manager) throws SmsCustomException;
    // r
    Manager getById(long id) throws SmsCustomException;
    Manager getByCredential(Credential credential) throws SmsCustomException;
    Set<Manager> getAll();
    // u
    Manager update(Manager manager) throws SmsCustomException;
    // d
    void remove(Manager manager) throws SmsCustomException;
}