package com.revature.sms.controllers.rest;

import com.revature.sms.entities.InterviewStatuses;
import com.revature.sms.services.InterviewStatusService;

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
@RequestMapping("interviewStatus")
public class InterviewStatusControllerImpl {

    @Autowired
    private InterviewStatusService interviewStatusService;

    public InterviewStatusControllerImpl(InterviewStatusService interviewStatusService) {

        super();
        this.interviewStatusService = interviewStatusService;
    }

    /**
     * When called this will always persist a unique interviewStatus in the
     * database.
     *
     * @param interveiwStatus - interviewStatus to be persisted.
     */
    @PostMapping
    public void add(@RequestBody InterviewStatuses interviewStatus) {

        interviewStatus.setId(0l);
        interviewStatusService.add(interviewStatus);
    }

    /**
     * When called this will always persist a unique interviewStatus in the
     * database.
     *
     * @param interveiwStatus - interviewStatus to be persisted.
     */
    @PostMapping("/add/all")
    public void addAll(@RequestBody Set<InterviewStatuses> interviewStatus) {

        for (InterviewStatuses is : interviewStatus)
            add(is);
    }

    /**
     * Gets a interveiwStatus with id.
     *
     * @param id - id of interveiwStatus to be retrieved.
     * @return interveiwStatus object from dataBase.
     */
    @GetMapping("/{id}")
    public InterviewStatuses findById(@PathVariable long id) {

        return interviewStatusService.findById(id);
    }

    /**
     * Gets all interveiwStatuss.
     *
     * @param all
     * @return all interveiwStatus objects from dataBase.
     */
    @GetMapping("/all")
    public Set<InterviewStatuses> findById() {

        return interviewStatusService.getAll();
    }

    /**
     * If the id exists, updates information. else creates a new row with
     * genrated id.
     *
     * @param interveiwStatus - data to be persisted.
     */
    @PutMapping
    public void update(@RequestBody InterviewStatuses interviewStatus) {

        interviewStatusService.update(interviewStatus);
    }

    /**
     * Deletes interveiwStatus with interveiwStatus.id
     *
     * @param interveiwStatus - holds the id to be deleted
     */
    @DeleteMapping
    public void delete(@RequestBody InterviewStatuses interviewStatus) {

        interviewStatusService.delete(interviewStatus);
    }
}
