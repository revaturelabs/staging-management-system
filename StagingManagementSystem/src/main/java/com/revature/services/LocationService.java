package com.revature.services;

import java.util.List;

import com.revature.entities.Location;

public interface LocationService {

	public void add(Location location);
	public void delete(Location location);
	
	public void update(Location location);
	
	public Location findById(long id);
	public List<Location> getAll();

}
