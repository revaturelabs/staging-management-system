package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Manager;
import com.revature.repositories.ManagerRepo;

public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepo managerRepo;
	
	public ManagerServiceImpl(ManagerRepo managerRepo){
		super();
		this.managerRepo = managerRepo;		
	}
	
	@Override
	public void add(Manager manager) {
		managerRepo.saveAndFlush(manager);
	}

	@Override
	public void delete(Manager manager) {
		managerRepo.delete(manager);
	}

	@Override
	public void update(Manager manager) {
		managerRepo.saveAndFlush(manager);
	}

	@Override
	public Manager findById(long id) {
		return managerRepo.findOne(id);
	}

	@Override
	public List<Manager> getAll() {
		return managerRepo.findAll();
	}


}
