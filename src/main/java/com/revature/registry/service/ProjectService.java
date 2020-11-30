package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<List<Project>> getAllProjects() {
        log.debug("Fetching all products: {}", projectRepository.findAll());
        return new ResponseEntity<List<Project>>(projectRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Project> getProjectById(int id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            log.debug("Fetching Project with id of {}: {}", project.get());
            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
        }
        log.error("Unable to GET. User with id {} not found.", id);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Project> createProject(Project project) {
        Project savedProject = projectRepository.save(project);
        log.debug("Project created with the following properties: {}", savedProject);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    public ResponseEntity<Project> updateProjectById(int id, Project newProject) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            newProject.setId(id);
            projectRepository.save(newProject);
            log.debug("Project with id: {}. Updated with the following proprerties: {}", id, newProject);
            return new ResponseEntity<Project>(project.get(), HttpStatus.OK);
        }

        log.error("Unable to update. User with id {} not found.", id);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Project> deleteProjectById(int id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.deleteById(id);
            log.debug("Project Deleted with id: {}", id);
            return new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
        }
        log.error("Unable to DELETE. User with id {} not found.", id);
        return new ResponseEntity<Project>(HttpStatus.BAD_REQUEST);
    }

}
