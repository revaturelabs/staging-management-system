package com.revature.controllers.rest;

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
import com.revature.entities.InterviewStatuses;
import com.revature.services.InterviewStatusService;
import com.revature.services.InterviewsService;

@RestController
@RequestMapping("interviews")
public class InterviewsControllerImpl {

	@Autowired
	private InterviewsService interviewsService;
	@Autowired
	private InterviewStatusService interviewStatusService;

	public InterviewsControllerImpl(InterviewsService interviewsService) {
		super();
		this.interviewsService = interviewsService;
	}

	@PostMapping
	public void add(@RequestBody Interview interviews) {
		InterviewStatuses status = interviewStatusService.findByStatus("SCHEDULED");
		interviews.setInterviewStatus(status);
		interviewsService.add(interviews);
	}

	@GetMapping("/{id}")
	public Interview findById(@PathVariable long id) {
		return interviewsService.findById(id);
	}

	@GetMapping("/associate/{associateId}")
	public Set<Interview> findByAssociate(@PathVariable long associateId) {
		return interviewsService.findByAssociateId(associateId);
	}

	@GetMapping("/client/{clientId}")
	public Set<Interview> findByClientId(@PathVariable long clientId) {
		return interviewsService.findByClientId(clientId);
	}

	@GetMapping("/status/{id}")
	public Set<Interview> findByInterviewStatusId(@PathVariable long id) {
		return interviewsService.findByInterviewStatus(id);
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

	@GetMapping("/next-five-days")
	public Set<Interview> findByNextFiveDays() {
		return interviewsService.nextFiveDays();
	}
}
