package com.revature.services;

import com.revature.entities.Location;

import java.util.Set;

public interface LocationService {

    public void add(Location location);
    public void delete(Location location);
    public void update(Location location);
    public Location findById(long id);
    public Set<Location> getAll();
}
