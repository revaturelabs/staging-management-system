package com.revature.sms.services;

import java.util.List;

import com.revature.sms.entities.Project;

public interface ProjectService {
	void addProject(Project project);
	public List<Project> getAll();
	public Project findById(long id);
}
