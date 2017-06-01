package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.entities.Checkin;
import com.revature.repositories.CheckinRepo;

public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private CheckinRepo checkinrepo;
	
	@Override
	public void add(Checkin checkin) {
		// TODO Auto-generated method stub

	}

}
