package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.entities.Permission;

public interface PermissionService
{
	public void add(Permission permission);
	public void delete(Permission permission);
	
	public void update(Permission permission);
	
	public Permission findById(long id);
	public List<Permission> getAll();
}
