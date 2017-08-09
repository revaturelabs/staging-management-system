package com.revature.controllers.rest;

import java.util.HashSet;
import java.util.List;
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

import com.revature.entities.Batch;
import com.revature.entities.BatchType;
import com.revature.entities.Skill;
import com.revature.services.BatchService;
import com.revature.services.SkillService;

@RestController
@RequestMapping("batch")
public class BatchControllerImpl {

	@Autowired
	private BatchService batchService;

	@Autowired
	private SkillService skillService;

	public BatchControllerImpl(BatchService batchService) {
		super();
		this.batchService = batchService;
	}

	@PostMapping
	public void addBatch(@RequestBody Batch batch) {
		batchService.add(batch);
	}

	/**
	 * Adds a new batch type, adds its skills if they don't already exist.
	 * 
	 * @param batchTypes
	 */
	@PostMapping("/types")
	public void addBatchTypes(@RequestBody Set<BatchType> batchTypes) {
		// this is business logic that should be moved to the service
		for (BatchType bt : batchTypes) {
			Set<Skill> skills = bt.getSkills();
			Set<Skill> persistantSkill = new HashSet<Skill>();
			for (Skill s : skills) {
				persistantSkill.add(skillService.addSkill(s));
			}

			bt.setSkills(persistantSkill);
		}
		batchService.addBatchTypes(batchTypes);

	}

	/**
	 * This is an end point that should really only be used for adding mock data
	 * 
	 * @param batches
	 */
	@PostMapping("/mockdata/addmultiple")
	public void addMockBatches(@RequestBody Set<Batch> batches) {
		batchService.addMockBatches(batches);
	}

	/**
	 * Deletes batch with location.id
	 * 
	 * @param batch
	 *            - holds the id to be deleted
	 */
	@DeleteMapping
	public void deleteBatch(@RequestBody Batch batch) {
		batchService.delete(batch);
	}

	/**
	 * If the id exists, updates information. else creates a new row with
	 * genrated id.
	 * 
	 * @param batch
	 *            - data to be persisted.
	 */
	@PutMapping
	public void updateBatch(@RequestBody Batch batch) {
		batchService.update(batch);
	}

	/**
	 * Gets a batch with id.
	 * 
	 * @param id
	 *            - id of batch to be retrieved.
	 * @return batch object from dataBase.
	 */
	@GetMapping("/{id}")
	public Batch findById(@PathVariable long id) {
		return batchService.findById(id);
	}

	/**
	 * Gets all Batches.
	 * 
	 * @param all
	 * @return all Batches objects from dataBase.
	 */
	@GetMapping("/all")
	public List<Batch> findAll() {
		return batchService.getAll();
	}
}
