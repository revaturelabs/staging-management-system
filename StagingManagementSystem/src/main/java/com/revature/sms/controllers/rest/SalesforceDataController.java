package com.revature.sms.controllers.rest;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Batch;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.BatchRepo;
import com.revature.sms.repositories.SalesforceRepo;
import com.revature.sms.security.models.SalesforceUser;
import com.revature.sms.services.JobService;
import com.revature.sms.services.security.SalesforceAuthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("sfdata")
public class SalesforceDataController {

	@Autowired
	private SalesforceRepo sd;

	@Autowired
	private BatchRepo bd;
	@Autowired
	private AssociateRepo ad;

	public SalesforceDataController(JobService jobService) {

		super();
	}

	/**
	 * When called this will always persist a unique job in the database.
	 *
	 * @param job
	 *            - job to be persisted.
	 */
	@GetMapping("/batches")
	public void getBatches(HttpSession session) {

		List<Batch> batches = sd.getRelevantBatches((SalesforceUser) session.getAttribute(SalesforceAuthorization.LM));
		bd.save(batches);
		for (Batch b : batches) {
			List<Associate> a = sd.getBatchTrainees(b.getSalesforceId(),
					(SalesforceUser) session.getAttribute(SalesforceAuthorization.LM));
			ad.save(a);
			ad.save(sd.getSpecificAssociates( ad.findByAssociateStatus_Status("STAGING"), (SalesforceUser) session.getAttribute(SalesforceAuthorization.LM)));
			
		}

	}
}
