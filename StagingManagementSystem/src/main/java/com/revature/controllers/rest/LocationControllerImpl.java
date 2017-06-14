package com.revature.controllers.rest;

import com.revature.entities.Location;
import com.revature.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("location")
public class LocationControllerImpl {

    @Autowired
    private LocationService locationService;

    public LocationControllerImpl(LocationService locationService) {

        super();
        this.locationService = locationService;
    }

    /**
     * When called this will always persist a unique location in the database.
     *
     * @param location - location to be persisted.
     */
    @PostMapping
    public void addLocation(@RequestBody Location location) {

        location.setId(0l);
        locationService.add(location);
    }

    @PostMapping("/add/all")
    public void addLocation(@RequestBody Set<Location> locations) {

        for (Location l : locations)
            addLocation(l);
    }

    /**
     * Deletes location with location.id
     *
     * @param location - holds the id to be deleted
     */
    @DeleteMapping
    public void deleteLocation(@RequestBody Location location) {

        locationService.delete(location);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param location - data to be persisted.
     */
    @PutMapping
    public void updateLocation(@RequestBody Location location) {

        locationService.update(location);
    }

    /**
     * Gets a location with id.
     *
     * @param id - id of location to be retrieved.
     * @return location object from dataBase.
     */
    @GetMapping("/{id}")
    public Location findById(@PathVariable long id) {

        return locationService.findById(id);
    }

    /**
     * Gets all locations.
     *
     * @param all
     * @return all location objects from dataBase.
     */
    @GetMapping("/all")
    public Set<Location> findAll() {

        return locationService.getAll();
    }
}
