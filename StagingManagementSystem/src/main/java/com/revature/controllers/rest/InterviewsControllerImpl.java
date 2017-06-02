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
	public void add(@RequestBody Interviews interviews) {
		interviewsService.add(interviews);
	}

	@GetMapping("/{id}")
	public Interviews findById(@PathVariable long id) {
		return interviewsService.findById(id);
	}

	@GetMapping("/{associate_Id}")
	public Interviews findByAssociate(long id) {
		return interviewsService.findByAssociateId(id);
	}
	
	/*@GetMapping("/{client_Id}")
	public Interviews findByClientId(long id) {
		return interviewsService.findByClientId(id);
	}*/
	
	@GetMapping("/{}")
	public Interviews findByInterviewStatusId(long id) {
		return interviewsService.findByInterviewStatus(id);
	}

//	@GetMapping("/{dateandtime}")
//	public Interviews findByScheduled(@PathVariable LocalDateTime dateandtime) {
//		return interviewsService.findByScheduled(dateandtime);
//	}

	@GetMapping("/all")
	public List<Interviews> findById() {
		return interviewsService.getAll();
	}

	@PutMapping
	public void update(@RequestBody Interviews interviews) {
		interviewsService.update(interviews);
	}

	@DeleteMapping
	public void delete(@RequestBody Interviews interviews) {
		interviewsService.delete(interviews);
	}
}
