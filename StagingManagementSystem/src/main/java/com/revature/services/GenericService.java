package com.revature.services;

import java.util.List;

public interface GenericService<T>
{
	void add(T obj);

	void delete(T obj);

	void update(T obj);

	T findById(long id);

	List<T> getAll();


}
