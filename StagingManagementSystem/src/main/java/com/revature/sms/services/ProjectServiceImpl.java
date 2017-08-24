package com.revature.sms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.sms.entities.Associate;
import com.revature.sms.entities.Project;
import com.revature.sms.repositories.AssociateRepo;
import com.revature.sms.repositories.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private AssociateRepo associateRepo;

	
	public ProjectServiceImpl(ProjectRepo projectRepo, AssociateRepo associateRepo) {
		super();
		this.projectRepo = projectRepo;
		this.associateRepo = associateRepo;
	}


	@Override
	public void addProject(Project project) {
		if("inactive".equals(project.getProjectStatus())){
			Set<Associate> em = new HashSet<>();
			project.setAssociates(em);
		}
		if (project.getProjectId() == 0) {
			Project p = projectRepo.saveAndFlush(project);
			project.getAssociates().forEach((Associate associate) -> {
				Associate ass = associateRepo.findOne(associate.getId());
				ass.setProject(p);
				associateRepo.saveAndFlush(ass);
			});
		} else {
			Project p = projectRepo.saveAndFlush(project);
			Set<Associate> associates = p.getAssociates();
			associates.forEach((Associate associate) -> {
				Associate ass = associateRepo.findOne(associate.getId());
				boolean contains = false;
				for (Associate retreivedAssociate : project.getAssociates()) {
					if (ass.getId() == retreivedAssociate.getId()) {
						contains = true;
					}
				}
				if (!contains) {
					ass.setProject(null);
					associateRepo.saveAndFlush(ass);
				}
			});

			project.getAssociates().forEach((Associate associate) -> {
				Associate ass = associateRepo.findOne(associate.getId());
				ass.setProject(p);
				associateRepo.saveAndFlush(ass);
			});
		}
	}


	@Override
	public List<Project> getAll() {
		return projectRepo.findAll();
	}


	@Override
	public Project findById(long id) {
		return projectRepo.getOne(id);
	}

}
