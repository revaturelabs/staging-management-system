package com.revature.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.sms.entities.AssociatesStatus;
import com.revature.sms.repositories.AssociatesStatusRepo;

@Component
public class AssociateStatusServiceImpl implements AssociatesStatusService {
	
	@Autowired
	private AssociatesStatusRepo associatesStatusRepo;

	public void updateStatus(AssociatesStatus status) {
		associatesStatusRepo.saveAndFlush(status);
	}

	@Override
	public List<AssociatesStatus> getAllStatusString() {
 		return associatesStatusRepo.findAll();
	}

}
