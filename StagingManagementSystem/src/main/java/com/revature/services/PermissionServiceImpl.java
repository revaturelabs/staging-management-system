package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Permission;
import com.revature.exceptions.SmsCustomException;
import com.revature.exceptions.badrequests.NonUniqueException;
import com.revature.exceptions.badrequests.NullReferenceException;
import com.revature.repositories.PermissionRepo;

public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepo permissionRepo;

	@Override
	public void add(Permission permission) throws SmsCustomException {
		if (permission == null) {
			throw new NullReferenceException("Permission is null.");
		}
		permission.validate();
		if (permissionRepo.findByLevel(permission.getLevel()) != null) {
			throw new NonUniqueException("Permission level already exists.");
		}
		permission = permissionRepo.save(permission);

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
		Set<Permission> permissions = new HashSet<Permission>(permissionRepo.findAll());
		
		return permissions;
	}

	@Override
	public void update(Permission permission) throws SmsCustomException{
		if(permission == null){
			throw new NullReferenceException("Permission is null.");
		}
		permission.validate();
		permission = permissionRepo.save(permission);

	}

	@Override
	public void remove(Permission permission) throws SmsCustomException {
		if(permission == null){
			throw new NullReferenceException("Permission is null.");
		}
		permissionRepo.delete(permission);

	}

}
