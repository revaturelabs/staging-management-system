package com.revature.sms.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.sms.entities.Project;
import com.revature.sms.services.ProjectService;

@RestController
@RequestMapping("project")
public class ProjectControllerImpl {

	@Autowired
	private ProjectService projectService;

	public ProjectControllerImpl(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
	@PostMapping
	public void addProject(@RequestBody Project project) {
		projectService.addProject(project);
	}
}
