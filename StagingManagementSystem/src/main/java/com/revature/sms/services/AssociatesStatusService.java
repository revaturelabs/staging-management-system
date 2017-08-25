package com.revature.sms.services;

import java.util.List;

import com.revature.sms.entities.AssociatesStatus;

public interface AssociatesStatusService {
	
	public void updateStatus(AssociatesStatus status);
	public List<AssociatesStatus> getAllStatusString();

}
