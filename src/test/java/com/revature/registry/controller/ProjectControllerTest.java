package com.revature.registry.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.registry.ProjectRegistryBackendApplication;
import com.revature.registry.model.Project;
import com.revature.registry.service.ProjectService;

@SpringBootTest(classes = ProjectRegistryBackendApplication.class)
@ExtendWith(SpringExtension.class)
class ProjectControllerTest {
	
	
	private MockMvc mockMvc;
	
	@Autowired
	@InjectMocks
	private ProjectController projectController;
	
	@MockBean
	private ProjectService projectService;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(projectController)
				.build();
	}

	@Test
	public void getAllProjectsReturnsData() throws Exception {
		mockMvc.perform(get("/api/project"))
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void should_get_appropriate_project_when_12isInput() throws Exception {
		//mock
		Project project = new Project();
		project.setId(12);
		project.setName("testProject");
		project.setDescription("a project simply just for testing");
		
		when(projectService.getProjectById((anyInt())))
		.thenReturn(new ResponseEntity<Project>(project,HttpStatus.OK));
		

		mockMvc.perform(get("/api/project/id/12"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("testProject"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(12))
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("a project simply just for testing"))
			;
	}
	
//	@Test
//	public void should_CreateProject_When_ValidRequest() {
//		when(projectService.createProject(any(Project.class))).thenReturn(new ResponseEntity<Project>());
//	}
	


}
