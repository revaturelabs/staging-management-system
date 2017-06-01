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

import com.revature.entities.InterviewStatus;
import com.revature.services.InterviewStatusService;

@RestController
@RequestMapping("interviewStatus")
public class InterviewStatusControllerImpl {
	@Autowired
	private InterviewStatusService interviewStatusService;
	
	public InterviewStatusControllerImpl(InterviewStatusService interviewStatusService) {
		super();
		this.interviewStatusService = interviewStatusService;
	}
	@PostMapping
	public void add(@RequestBody InterviewStatus interviewStatus) {
		interviewStatusService.add(interviewStatus);
	}

	@GetMapping("/{id}")
	public InterviewStatus findById(@PathVariable long id) {		
		return interviewStatusService.findById(id);
	}

	@GetMapping("/all")
	public List<InterviewStatus> findById() {
		return interviewStatusService.getAll();
	}

	@PutMapping
	public void update(@RequestBody InterviewStatus interviewStatus) {
		interviewStatusService.update(interviewStatus);
	}

	@DeleteMapping
	public void delete(@RequestBody InterviewStatus interviewStatus) {
		interviewStatusService.delete(interviewStatus);
	}
}
