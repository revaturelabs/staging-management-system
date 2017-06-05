package com.revature.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Location;
import com.revature.services.LocationService;

@RestController
@RequestMapping("location")
public class LocationControllerImpl {

	@Autowired
	private LocationService locationService;

	public LocationControllerImpl(LocationService locationService) {
		super();
		this.locationService = locationService;
	}

	@PostMapping
	public void addLocation(@RequestBody Location location) {
		locationService.add(location);
	}

	@DeleteMapping
	public void deleteLocation(@RequestBody Location location) {
		locationService.delete(location);
	}

	@PutMapping
	public void updateLocation(@RequestBody Location location) {
		locationService.update(location);
	}

	@GetMapping("/{id}")
	public Location findById(@PathVariable long id) {
		return locationService.findById(id);
	}

	@GetMapping("/all")
	public List<Location> findById() {
		return locationService.getAll();
	}
}
