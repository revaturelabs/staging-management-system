package com.revature.controllers.rest;

import java.util.ArrayList;
import java.util.Random;
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

import com.revature.entities.BatchType;
import com.revature.entities.InterviewQuestion;
import com.revature.services.BatchTypeService;
import com.revature.services.InterviewQuestionService;

@RestController
@RequestMapping("interviewQuestions")
public class InterviewQuestionControllerImpl {

	@Autowired
	private InterviewQuestionService interviewQuestionService;

	@Autowired
	private BatchTypeService batchTypeService;

	private static final int PROB_CONTAINER = 100000;
	
	public InterviewQuestionControllerImpl(InterviewQuestionService interviewQuestionService) {
		super();
		this.interviewQuestionService = interviewQuestionService;
	}

	@PostMapping
	public void addInterviewQuestion(@RequestBody InterviewQuestion interviewQ) {
		interviewQuestionService.add(interviewQ);
	}

	 @PostMapping("/add/all")
	  public void addMockInterviewQuestion(@RequestBody Set<InterviewQuestion> interviewQs) {
	   
	   Set<BatchType> batches = batchTypeService.getAll();
	   ArrayList<BatchType> batchList = new ArrayList<>(batches);
	   Random r = new Random();
	   for(InterviewQuestion iq : interviewQs)
	   {
	     int prob = r.nextInt()% PROB_CONTAINER /2 + PROB_CONTAINER /2;
	     iq.setBatchType(functionGetBatchType(prob, batchList));
	     addInterviewQuestion(iq);
	   }
	  }
	 
	 /**
	  * Recieves an integer btween [0,99] and reteruns a Batch type
	  * @param i
	  */
	 private BatchType functionGetBatchType(int i, ArrayList<BatchType> batches){
	   int setSize = batches.size();
	   int usedProb = 0;
	   for(int rank = 0; rank < setSize; rank++)
	   {
	     int rankProb = (PROB_CONTAINER - usedProb)*6/10;
	      if(usedProb < i && i < rankProb + usedProb)//60% of the remaining probability that a batch at rank index is chosen
	      {
	        return batches.get(rank);
	      }
	      usedProb += rankProb;
	   }
	   return batches.get(setSize - 1);
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