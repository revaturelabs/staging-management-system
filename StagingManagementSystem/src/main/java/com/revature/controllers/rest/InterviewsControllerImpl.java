package com.revature.controllers.rest;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Interview;
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
	public void add(@RequestBody Interview interviews) {
		interviewsService.add(interviews);
	}

	@GetMapping("/{id}")
	public Interview findById(@PathVariable long id) {
		return interviewsService.findById(id);
	}

	@GetMapping("/associate/{associate_Id}")
	public Interview findByAssociate(long id) {
		return interviewsService.findByAssociateId(id);
	}

	@GetMapping("/client/{client_Id}")
	public Interview findByClientId(long id) {
		return interviewsService.findByClientId(id);
	}

	@GetMapping("/status/{interview_Status_Id}")
	public Interview findByInterviewStatusId(long id) {
		return interviewsService.findByInterviewStatus(id);
	}

	@GetMapping("/date/{dateandtime}")
	public Interview findByScheduled(@PathVariable LocalDateTime dateandtime) {
		return interviewsService.findByScheduled(dateandtime);
	}

	@GetMapping("/all")
	public Set<Interview> findById() {
		return interviewsService.getAll();
	}

	@PutMapping
	public void update(@RequestBody Interview interviews) {
		interviewsService.update(interviews);
	}

	@DeleteMapping
	public void delete(@RequestBody Interview interviews) {
		interviewsService.delete(interviews);
	}
}
