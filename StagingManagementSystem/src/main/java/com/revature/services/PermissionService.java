package com.revature.services;

import java.util.Set;

import com.revature.entities.Permission;
import com.revature.exceptions.SmsCustomException;

public interface PermissionService {

	// c
	Permission add(Permission permission) throws SmsCustomException;

	// r
	Permission getById(long id) throws SmsCustomException;

	Permission getByLevel(String level) throws SmsCustomException;

	Set<Permission> getAll();

	// u
	Permission update(Permission permission) throws SmsCustomException;

	// d
	void remove(Permission permission) throws SmsCustomException;
}
