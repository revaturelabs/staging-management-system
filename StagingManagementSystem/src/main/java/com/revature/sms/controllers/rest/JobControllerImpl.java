package com.revature.sms.controllers.rest;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Job;
import com.revature.sms.services.JobService;

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
@RequestMapping("job")
public class JobControllerImpl {

    @Autowired
    private JobService jobService;

    public JobControllerImpl(JobService jobService) {

        super();
        this.jobService = jobService;
    }

    /**
     * When called this will always persist a unique job in the database.
     *
     * @param job - job to be persisted.
     */
    @PostMapping
    public void addjob(@RequestBody Job job) {

        job.setId(0l);
        jobService.add(job);
    }

    /**
     * Deletes job with job.id
     *
     * @param job - holds the id to be deleted
     */
    @DeleteMapping
    public void deleteJob(@RequestBody Job job) {

        jobService.delete(job);
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param job - data to be persisted.
     */
    @PutMapping
    public void updateJob(@RequestBody Job job) {

        jobService.update(job);
    }

    /**
     * Gets a job with id.
     *
     * @param id - id of job to be retrieved.
     * @return job object from dataBase.
     */
    @GetMapping("/{id}")
    public Job findById(@PathVariable long id) {

        return jobService.findById(id);
    }

    /**
     * Gets a job with id.
     *
     * @param id - id of job to be retrieved.
     * @return job object from dataBase.
     */
    @PostMapping("/associate")
    public Set<Job> findById(@RequestBody Associate associate) {

        return jobService.findByAssociate(associate);
    }

    /**
     * Gets all jobs.
     *
     * @param all
     * @return all job objects from dataBase.
     */
    @GetMapping("/all")
    public Set<Job> findAll() {

        return jobService.getAll();
    }
}
