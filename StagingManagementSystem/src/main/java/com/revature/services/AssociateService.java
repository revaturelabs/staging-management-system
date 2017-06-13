package com.revature.services;

import java.util.Set;

import com.revature.entities.Associate;

public interface AssociateService {
	public Associate getById(long id);

	public void add(Associate associate);

	public void delete(Associate associate);

	public void update(Associate associate);

	public Set<Associate> getAll();

  public Set<Associate> getAllActive();
}
