package com.revature.sms.services;

import com.revature.sms.entities.Permission;
import com.revature.sms.exceptions.SmsCustomException;
import com.revature.sms.exceptions.badrequests.NonUniqueException;
import com.revature.sms.exceptions.badrequests.NullReferenceException;
import com.revature.sms.repositories.PermissionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;

    public PermissionServiceImpl() {

        super();
    }

    public PermissionServiceImpl(PermissionRepo permissionRepo) {

        super();
        this.permissionRepo = permissionRepo;
    }

    @Override
    public Permission add(Permission permission) throws SmsCustomException {

        if (permission == null) {
            throw new NullReferenceException("Permission is null.");
        }
        if (permissionRepo.findByLevel(permission.getLevel()) != null) {
            throw new NonUniqueException("Permission level already exists.");
        }
        return permissionRepo.saveAndFlush(permission);
    }

    @Override
    public Permission getById(long id) throws SmsCustomException {

        Permission permission = permissionRepo.findOne(id);
        if (permission == null) {
            throw new NullReferenceException("Permission ID could not be found.");
        }
        return permission;
    }

    @Override
    public Permission getByLevel(String level) throws SmsCustomException {

        if (level == null) {
            throw new NullReferenceException("Passed in level argument is null.");
        }
        Permission permission = permissionRepo.findByLevel(level);
        if (permission == null) {
            throw new NullReferenceException("No permission found with given level.");
        }
        return permission;
    }

    @Override
    public Set<Permission> getAll() {

        return new HashSet<>(permissionRepo.findAll());
    }

    @Override
    public Permission update(Permission permission) throws SmsCustomException {

        if (permission == null) {
            throw new NullReferenceException("Permission is null.");
        }
        return permissionRepo.saveAndFlush(permission);
    }

    @Override
    public void remove(Permission permission) throws SmsCustomException {

        if (permission == null) {
            throw new NullReferenceException("Permission is null.");
        }
        permissionRepo.delete(permission);
    }
}
