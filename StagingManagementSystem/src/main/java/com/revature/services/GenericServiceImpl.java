package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.revature.entities.SmsEntities;

@Service
public class GenericServiceImpl<T extends JpaRepository<SmsEntities, Long>> implements GenericService<SmsEntities> 
{
	@Autowired
	T repo;

	public GenericServiceImpl(T repo) {
		super();
		this.repo = repo;
	}

	@Override	
	public void add(SmsEntities obj)
	{
		repo.saveAndFlush(obj);
	}

	@Override
	public void delete(SmsEntities obj)
	{
		repo.delete(obj);
	}

	@Override
	public void update(SmsEntities obj)
	{
		repo.saveAndFlush(obj);
	}

	@Override
	public SmsEntities findById(long id)
	{
		return repo.getOne(id);
	}

	@Override
	public List<SmsEntities> getAll()
	{
		return repo.findAll();
	}

}
