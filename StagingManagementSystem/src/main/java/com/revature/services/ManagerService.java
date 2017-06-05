package com.revature.services;

import java.util.List;

import com.revature.entities.Manager;

public interface ManagerService {
	
	public void add(Manager manager);

	public void delete(Manager manager);

	public void update(Manager manager);

	public Manager findById(long id);

	public List<Manager> getAll();

}
