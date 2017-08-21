package com.revature.sms.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public void addProject(@RequestBody Project project) {
		projectService.addProject(project);
	}
	
	// get all projects
	@GetMapping("/all")
	public List<Project> findAll() {
		return projectService.getAll();
	}
	
	// get a project by id
	@GetMapping("/{id}")
	public Project findById(@PathVariable long id) {
		return projectService.findById(id);
	}
}
