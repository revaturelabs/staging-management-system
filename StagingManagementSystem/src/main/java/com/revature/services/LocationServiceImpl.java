package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Location;
import com.revature.repositories.LocationRepo;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	LocationRepo locationRepo;

	public LocationServiceImpl(LocationRepo locationRepo) {
		super();
		this.locationRepo = locationRepo;
	}

	@Override
	public void add(Location location) {
		locationRepo.saveAndFlush(location);
	}

	@Override
	public Set<Location> getAll() {
		return new HashSet<Location>(locationRepo.findAll());
	}

	@Override
	public Location findById(long id) {
		return locationRepo.getOne(id);
	}

	@Override
	public void delete(Location location) {
		locationRepo.delete(location);
	}

	@Override
	public void update(Location location) {
		locationRepo.saveAndFlush(location);
	}
}
