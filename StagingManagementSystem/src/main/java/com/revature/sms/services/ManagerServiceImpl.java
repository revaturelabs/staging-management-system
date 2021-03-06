package com.revature.sms.services;

import com.revature.sms.entities.Credential;
import com.revature.sms.entities.Manager;
import com.revature.sms.entities.Permission;
import com.revature.sms.exceptions.SmsCustomException;
import com.revature.sms.exceptions.badrequests.NullReferenceException;
import com.revature.sms.repositories.ManagerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private PermissionService permissionService;

    public ManagerServiceImpl() {

        super();
    }

    public ManagerServiceImpl(ManagerRepo managerRepo, CredentialService credentialService, PermissionService permissionService) {

        super();
        this.managerRepo = managerRepo;
        this.credentialService = credentialService;
        this.permissionService = permissionService;
    }

    // c
    @Override
    public Manager add(Manager manager) throws SmsCustomException {

        if (manager == null) {
            throw new NullReferenceException("Manager is null.");
        }
        if (manager.getPermission() == null) {
            throw new NullReferenceException("Manager permission is null.");
        }
        Permission permission = permissionService.getByLevel(manager.getPermission().getLevel());
        manager.setPermission(permission);
        Credential credential = manager.getCredential();
        credentialService.add(credential);
        manager.setCredential(credential);
        return managerRepo.saveAndFlush(manager);
    }

    // r
    @Override
    public Manager getById(long id) throws SmsCustomException {

        Manager manager = managerRepo.getOne(id);
        if (manager == null) {
            throw new NullReferenceException("Manager ID could not be found.");
        }
        return manager;
    }

    @Override
    public Manager getByCredential(Credential credential) throws SmsCustomException {

        if (credential == null) {
            throw new NullReferenceException("Passed in credential argument is null.");
        }
        Manager manager = managerRepo.findByCredentialUsernameAndCredentialPassword(credential.getUsername(), credential.getPassword());
        if (manager == null) {
            throw new NullReferenceException("No manager found with given credential.");
        }
        return manager;
    }

    @Override
    public Set<Manager> getAll() {

        return new HashSet<>(managerRepo.findAll());
    }

    // u
    @Override
    public Manager update(Manager manager) throws SmsCustomException {

        if (manager == null) {
            throw new NullReferenceException("Manager is null.");
        }
        Credential credential = manager.getCredential();
        credentialService.add(credential);
        manager.setCredential(credential);
        return managerRepo.saveAndFlush(manager);
    }

    // d
    @Override
    public void remove(Manager manager) throws SmsCustomException {

        if (manager == null) {
            throw new NullReferenceException("Manager is null.");
        }
        Credential credential = manager.getCredential();
        credentialService.remove(credential);
        managerRepo.delete(manager);
    }
}
