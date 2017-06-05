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

import com.revature.entities.InterviewQuestion;
import com.revature.services.InterviewQuestionService;

@RestController
@RequestMapping("interviewQuestions")
public class InterviewQuestionControllerImpl {

	@Autowired
	InterviewQuestionService interviewQuestionService;

	public InterviewQuestionControllerImpl(InterviewQuestionService interviewQuestionService) {
		super();
		this.interviewQuestionService = interviewQuestionService;
	}

	@PostMapping
	public void addInterviewQuestion(@RequestBody InterviewQuestion interviewQ) {
		interviewQuestionService.add(interviewQ);
	}

	@DeleteMapping
	public void deleteInterviewQuestion(@RequestBody InterviewQuestion interviewQ) {
		interviewQuestionService.delete(interviewQ);
	}

	@PutMapping
	public void updateInterviewQuestion(@RequestBody InterviewQuestion interviewQ) {
		interviewQuestionService.update(interviewQ);
	}

	@GetMapping("/{id}")
	public InterviewQuestion findById(@PathVariable long id) {
		return interviewQuestionService.findById(id);
	}

	@GetMapping("/all")
	public Set<InterviewQuestion> findById() {
		return interviewQuestionService.getAll();
	}
}