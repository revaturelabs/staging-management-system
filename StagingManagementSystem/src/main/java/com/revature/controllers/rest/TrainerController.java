package com.revature.controllers.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Trainer;
import com.revature.services.TrainerService;

@RestController
@RequestMapping("trainer")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;

	public TrainerController(TrainerService trainerService) {
		super();
		this.trainerService = trainerService;
	}

	@PostMapping("/addmultiple")
	public void addTrainers(@RequestBody Set<Trainer> trainers) {
		trainerService.addTrainers(trainers);
	}
}
