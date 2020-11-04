package com.revature.registry.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Lists;
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

		//mock the return of getAllProjects from ProjectService
		
		Project project1 = new Project();
		project1.setId(12);
		project1.setName("testProject1");
		project1.setDescription("a project simply just for testing");
		
		Project project2 = new Project();
		project2.setId(13);
		project2.setName("testProject2");
		project2.setDescription("a project simply just for testing");
		
		List<Project> projects = Lists.newArrayList(project1,project2);
		
		when(projectService.getAllProjects())
			.thenReturn(new ResponseEntity<List<Project>>(projects, HttpStatus.OK));
		
		mockMvc.perform(get("/api/project"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))			
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("testProject1"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("testProject2"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(12))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(13));
		
	}
	
	@Test
	public void should_get_appropriate_project_when_12isInput() throws Exception {
		
		//mock the return of getProjectById from ProjectService
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
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("a project simply just for testing"));
	}
	
	


}
