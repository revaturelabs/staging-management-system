package com.revature.controllers.rest;

import com.revature.entities.Batch;
import com.revature.entities.BatchType;
import com.revature.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("batch")
public class BatchControllerImpl {

    @Autowired
    private BatchService batchService;

    public BatchControllerImpl(BatchService batchService) {

        super();
        this.batchService = batchService;
    }

    @PostMapping
    public void addBatch(@RequestBody Batch batch) {

        System.out.println(batch);
        batchService.add(batch);
    }

    @PostMapping("/types")
    public void addBatchTypes(@RequestBody Set<BatchType> batchTypes) {

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
     * @param batch - holds the id to be deleted
     */
    @DeleteMapping
    public void deleteTrainer(@RequestBody Batch batch) {

        batchService.delete(batch);
    }

    /**
     * If the id exists, updates information.
     * else creates a new row with genrated id.
     *
     * @param batch - data to be persisted.
     */
    @PutMapping
    public void updateTrainer(@RequestBody Batch batch) {

        batchService.update(batch);
    }

    /**
     * Gets a batch with id.
     *
     * @param id - id of batch to be retrieved.
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
