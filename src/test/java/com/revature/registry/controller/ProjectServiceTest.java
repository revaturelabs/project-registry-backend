package com.revature.registry.controller;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.registry.ProjectRegistryBackendApplication;
import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;
import com.revature.registry.service.ProjectService;

@SpringBootTest(classes = ProjectRegistryBackendApplication.class)

@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

	@MockBean
	private ProjectRepository projectRepository;

	@Test
	public void when_getProjectByID_return_correct_list() {
		// mock the return of getAllProjects from ProjectService

				Project project1 = new Project();
				project1.setId(12);
				project1.setName("testProject1");
				project1.setDescription("a project simply just for testing");

				Project project2 = new Project();
				project2.setId(13);
				project2.setName("testProject2");
				project2.setDescription("a project simply just for testing");

				List<Project> projects = Lists.newArrayList(project1, project2);
				ResponseEntity<List<Project>> expected = new ResponseEntity<>(projects,HttpStatus.OK);

				when(projectRepository.findAll()).thenReturn(projects);
				
		//check to see if the method returns the correct data
		ResponseEntity<List<Project>> output = projectService.getAllProjects();
		assertThat(output).isEqualTo(expected);
	}
	
	@Test
	public void when_getProjectByID_then_return_correct_project() {
		// mock the return of getAllProjects from ProjectService

				Project project = new Project();
				project.setId(12);
				project.setName("testProject1");
				project.setDescription("a project simply just for testing");

				ResponseEntity<Project> expected = new ResponseEntity<Project>(project,HttpStatus.OK);
				when(projectRepository.findById(12)).thenReturn(Optional.of(project));
				
		//check to see if the method returns the correct data
		ResponseEntity<Project> output = projectService.getProjectById(12);
		assertThat(output).isEqualTo(expected);
	}

}
