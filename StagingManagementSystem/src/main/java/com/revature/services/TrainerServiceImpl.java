package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.repositories.TrainerRepo;

@Service
public class TrainerServiceImpl extends GenericServiceImpl<TrainerRepo>
{
	public TrainerServiceImpl(TrainerRepo repo)
	{
		super(repo);
	}

}
