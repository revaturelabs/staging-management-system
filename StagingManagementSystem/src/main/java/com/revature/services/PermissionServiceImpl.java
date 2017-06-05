package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Permission;
import com.revature.repositories.PermissionRepo;

@Service
public class PermissionServiceImpl implements PermissionService
{
	@Autowired
	PermissionRepo permissionRepo;

	public PermissionServiceImpl(PermissionRepo permissionRepo) {
		super();
		this.permissionRepo = permissionRepo;
	}

	@Override
	public void add(Permission permission)
	{
		permissionRepo.saveAndFlush(permission);
	}

	@Override
	public void delete(Permission permission)
	{
		permissionRepo.delete(permission);
	}

	@Override
	public void update(Permission permission)
	{
		permissionRepo.saveAndFlush(permission);
	}

	@Override
	public Permission findById(long id)
	{
		return permissionRepo.getOne(id);
	}

	@Override
	public List<Permission> getAll()
	{
		return permissionRepo.findAll();
	}

}
