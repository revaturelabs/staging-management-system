package com.revature.controllers.rest;

import com.revature.entities.BatchType;
import com.revature.services.BatchTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("batchtype")
public class BatchTypeControllerImpl {

    @Autowired
    private BatchTypeService batchTypeService;

    public BatchTypeControllerImpl(BatchTypeService batchTypeService) {

        super();
        this.batchTypeService = batchTypeService;
    }

    /**
     * When called this will always persist a unique BatchType in the database.
     *
     * @param batchType - batchType to be persisted.
     */
    @PostMapping
    public BatchType addBatchType(@RequestBody BatchType batchType) {

        batchType.setId(0l);
        return batchTypeService.add(batchType);
    }

    /**
     * Deletes BatchType with location.id
     *
     * @param batchType - holds the id to be deleted
     */
    @DeleteMapping
    public void deleteBatchType(@RequestBody BatchType batchType) {

        batchTypeService.delete(batchType);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param BatchType - data to be persisted.
     */
    @PutMapping
    public void updateBatchType(@RequestBody BatchType batchType) {

        batchTypeService.update(batchType);
    }

    /**
     * Gets a batchType with id.
     *
     * @param id - id of batchType to be retrieved.
     * @return batchType object from dataBase.
     */
    @GetMapping("/{id}")
    public BatchType findById(@PathVariable long id) {

        return batchTypeService.findById(id);
    }

    /**
     * Gets all batchTypes.
     *
     * @param all
     * @return all batchType objects from dataBase.
     */
    @GetMapping("/all")
    public Set<BatchType> findAll() {

        return batchTypeService.getAll();
    }
}
