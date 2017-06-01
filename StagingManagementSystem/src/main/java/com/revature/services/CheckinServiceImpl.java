package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entities.Checkin;
import com.revature.repositories.CheckinRepo;

@Service
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private CheckinRepo checkinrepo;
	
	@Override
	public void add(Checkin checkin) {

	}

}
