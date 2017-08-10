package com.revature.sms.services;

import com.revature.sms.entities.Location;
import com.revature.sms.repositories.LocationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
