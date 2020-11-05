package com.revature.registry.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.registry.ProjectRegistryBackendApplication;
import com.revature.registry.repository.ProjectRepository;
import com.revature.registry.service.ProjectService;

@SpringBootTest(classes = ProjectRegistryBackendApplication.class)

@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;
	
	@MockBean
	private ProjectRepository projectRepository;
	

}
