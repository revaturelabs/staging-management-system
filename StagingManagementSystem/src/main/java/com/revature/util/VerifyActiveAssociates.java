package com.revature.util;

import java.util.Set;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.entities.Associate;
import com.revature.services.AssociateService;
import com.revature.services.JobService;

@Component
@Aspect
public class VerifyActiveAssociates
{
	
	@Autowired
	AssociateService associateService;
	@Autowired
	JobService jobService;
    
    @Scheduled(
            cron = "0,30 * * * * *")
    public void verifyActive() {
    		System.out.println("running... ");

            Set<Associate> associates = associateService.getAll();
            for(Associate a : associates)
            {
            	System.out.println(a);
            	a.setJobs(jobService.findByAssociate(a));
            	a.setActive();
            	associateService.update(a);
            }
 
    }
	


}

