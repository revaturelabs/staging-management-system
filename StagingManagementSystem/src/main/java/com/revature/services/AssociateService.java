package com.revature.services;

import com.revature.entities.Associate;

public interface AssociateService {

	Associate getById(long id);

	void add(Associate associate);

}
