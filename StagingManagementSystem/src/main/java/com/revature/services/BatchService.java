package com.revature.services;

import java.util.Set;

import com.revature.entities.Batch;

public interface BatchService {

	void add(Batch batch);

	Set<Batch> findByInstructor(String name);

}
