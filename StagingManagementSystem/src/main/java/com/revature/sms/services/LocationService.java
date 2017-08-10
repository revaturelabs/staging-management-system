package com.revature.sms.services;

import java.util.Set;

import com.revature.sms.entities.Location;

public interface LocationService {

    public void add(Location location);
    public void delete(Location location);
    public void update(Location location);
    public Location findById(long id);
    public Set<Location> getAll();
}
