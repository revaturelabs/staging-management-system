package com.revature.controllers.rest;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Batch;
import com.revature.services.BatchService;

@RestController
@RequestMapping("batch")
public class BatchControllerImpl {
	// Probably implement aspect logging
	Logger log = Logger.getRootLogger();

	@Autowired
	private BatchService batchService;

	public BatchControllerImpl(BatchService batchService) {
		super();
		this.batchService = batchService;
	}

	@PostMapping
	public void addBatch(@RequestBody Batch batch) {
		batchService.add(batch);
		log.info("Successfully added batch");
	}

	@GetMapping("/instructor/{name}")
	public Set<Batch> findByInstructor(@PathVariable String name) {
		return batchService.findByInstructor(name);
	}

}
