package com.revature.registry.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.revature.registry.model.Project;
import com.revature.registry.model.Status;
import com.revature.registry.repository.ProjectRepository;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectService {
    private static Logger log = Logger.getLogger(ProjectService.class);
    
    
    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<List<Project>> getAllProjects() {
 //       log.setLevel(Level.DEBUG); if Logger is not working, use setLevel
        log.debug("Fetching all projects: " + projectRepository.findAll());
        return ResponseEntity.ok(projectRepository.findAll());
    }

    public ResponseEntity<Project> getProjectById(int id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            log.debug("Fetching Project with id of: " + project.get());
            return ResponseEntity.ok(project.get());
        }
        log.error("Unable to GET. User with id " + id + " not found.");
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Project> createProject(Project project) {
        Project savedProject = projectRepository.save(project);
        log.debug("Project created with the following properties: " + savedProject.toString());
        String location = String.format("/api/project/%s", savedProject.getId());
        return ResponseEntity.created(URI.create(location)).body(savedProject);
    }

    public ResponseEntity<Project> updateProjectById(int id, Project newProject) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            newProject.setId(id);
            projectRepository.save(newProject);
            log.debug("Project with id: " + id + " Updated with the following proprerties: " + newProject);
            return ResponseEntity.ok(project.get());
        }

        log.error("Unable to update. User with id " + id + " not found.");
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Project> deleteProjectById(int id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.deleteById(id);
            log.debug("Project Deleted with id: " + id);
            return ResponseEntity.noContent().build();
        }
        log.error("Unable to DELETE. User with id " + id);
        return ResponseEntity.badRequest().build();
    }
    
    public ResponseEntity<List<Project>> getAllActiveProjects(int statusId) {
        Status s = new Status();
        s.setId(statusId);
        log.debug("Fetching all active projects: " + projectRepository.getProjectByStatus(s));
        return ResponseEntity.ok(projectRepository.getProjectByStatus(s));
    }

}
