package com.revature.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Interviews;
import com.revature.services.InterviewsService;

@RestController
@RequestMapping("interviews")
public class InterviewsControllerImpl {
	
	@Autowired
	private InterviewsService interviewsService;
	
	public InterviewsControllerImpl(InterviewsService interviewsService) {
		super();
		this.interviewsService = interviewsService;
	}
	
	@PostMapping
	public void addInterviews(@RequestBody Interviews interviews) {
		interviewsService.add(interviews);
	}
}
