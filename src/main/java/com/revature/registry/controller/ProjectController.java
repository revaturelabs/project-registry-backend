package com.revature.registry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.registry.model.Project;
import com.revature.registry.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("")
	public List<Project> getAllProjects(){
		return projectService.getAllProjects();
	}
	
	@PostMapping("")
	public Project postProject(@RequestBody Project project) {
		System.out.println(project);
		return projectService.saveProject(project);
	}
}
