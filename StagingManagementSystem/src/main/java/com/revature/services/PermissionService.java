package com.revature.services;

import java.util.Set;

import com.revature.entities.Permission;
import com.revature.exceptions.SMSCustomException;

public interface PermissionService {

	// c
	void add(Permission permission) throws SMSCustomException;

	// r
	Permission getById(long id) throws SMSCustomException;

	Permission getByLevel(String level) throws SMSCustomException;

	Set<Permission> getAll();

	// u
	void update(Permission permission) throws SMSCustomException;

	// d
	void remove(Permission permission) throws SMSCustomException;
}
