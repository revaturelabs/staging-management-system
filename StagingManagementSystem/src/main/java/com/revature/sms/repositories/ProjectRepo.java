package com.revature.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.sms.entities.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{
	
	Project getByProjectId(int projectId);
}
