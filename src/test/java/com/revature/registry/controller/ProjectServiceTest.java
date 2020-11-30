package com.revature.registry.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.revature.registry.ProjectRegistryBackendApplication;
import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;
import com.revature.registry.service.ProjectService;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = ProjectRegistryBackendApplication.class)
@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @Test
    void when_getProjectByID_return_correct_list() {
        // mock the return of getAllProjects from ProjectRepository

        Project project1 = new Project();
        project1.setId(12);
        project1.setName("testProject1");
        project1.setDescription("a project simply just for testing");

        Project project2 = new Project();
        project2.setId(13);
        project2.setName("testProject2");
        project2.setDescription("a project simply just for testing");

        List<Project> projects = Lists.newArrayList(project1, project2);
        ResponseEntity<List<Project>> expected = new ResponseEntity<>(projects, HttpStatus.OK);

        when(projectRepository.findAll()).thenReturn(projects);

        // check to see if the method returns the correct data
        ResponseEntity<List<Project>> output = projectService.getAllProjects();
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_getProjectByID_then_return_correct_project() {
        // mock the return of getProjectById from ProjectRepository

        Project project = new Project();
        project.setId(12);
        project.setName("testProject1");
        project.setDescription("a project simply just for testing");

        ResponseEntity<Project> expected = new ResponseEntity<Project>(project, HttpStatus.OK);
        when(projectRepository.findById(12)).thenReturn(Optional.of(project));

        // check to see if the method returns the correct data
        ResponseEntity<Project> output = projectService.getProjectById(12);
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_getProjectByID_bad_id_then_return_bad_request() {
        // no need to mock anything since projectRepository.findById() will return an
        // empty optional
        ResponseEntity<Project> expected = new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());

        // check to see if the method returns the correct data
        ResponseEntity<Project> output = projectService.getProjectById(13);
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_createProject_return_created_project() {
        // mock the return of getProjectById from ProjectRepository

        Project project = new Project();
        project.setId(12);
        project.setName("testProject1");
        project.setDescription("a project simply just for testing");

        ResponseEntity<Project> expected = new ResponseEntity<Project>(project, HttpStatus.CREATED);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // check to see if the method returns the correct data
        Project input = new Project();
        input.setName("testProject1");
        input.setDescription("a project simply just for testing");
        ResponseEntity<Project> output = projectService.createProject(input);
        assertThat(output.getBody()).isEqualToComparingOnlyGivenFields(expected.getBody(), "name", "description");
        assertThat(output.getBody().getId()).isNotZero();
    }

    @Test
    void when_updateProjectByID_then_return_updated_project() {
        // mock the return of updateProjectById from ProjectRepository

        Project project = new Project();
        project.setId(12);
        project.setName("testProject1");
        project.setDescription("a project simply just for testing");

        ResponseEntity<Project> expected = new ResponseEntity<Project>(project, HttpStatus.OK);
        // mock check to see if object with id exists in db
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        // mock the update
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // check to see if the method returns the correct data
        Project input = new Project();
        input.setName("testProject1");
        input.setDescription("a project simply just for testing");
        ResponseEntity<Project> output = projectService.updateProjectById(12, input);
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_updateProjectByID_and_id_invalid_then_return_updated_project() {
        // mock the return of updateProjectById from ProjectRepository

        Project project = new Project();
        project.setId(12);
        project.setName("testProject1");
        project.setDescription("a project simply just for testing");

        ResponseEntity<Project> expected = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        // mock check to see that object with id does not exist in db
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());
        // mock the update
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // check to see if the method returns the correct data
        Project input = new Project();
        input.setName("testProject1");
        input.setDescription("a project simply just for testing");
        ResponseEntity<Project> output = projectService.updateProjectById(12, input);
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_deleteProjectById_then_return_no_content() {
        // mock the return of findById from ProjectRepository

        Project project = new Project();
        project.setId(12);
        project.setName("testProject1");
        project.setDescription("a project simply just for testing");

        ResponseEntity<Project> expected = new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
        // mock check to see if object with id exists in db
        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        // you don't have to mock the delete since it has a void return type

        // check to see if the method returns the correct data
        ResponseEntity<Project> output = projectService.deleteProjectById(12);
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void when_deleteProjectByID_and_id_invalid_then_return_bad_request() {
        // mock the return of updateProjectById from ProjectRepository

        Project project = new Project();
        project.setId(12);
        project.setName("testProject1");
        project.setDescription("a project simply just for testing");

        ResponseEntity<Project> expected = new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
        // mock check to see if object with id exists in db
        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());
        // you don't have to mock the delete since it has a void return type

        // check to see if the method returns the correct data
        ResponseEntity<Project> output = projectService.deleteProjectById(12);
        assertThat(output).isEqualTo(expected);
    }

}
