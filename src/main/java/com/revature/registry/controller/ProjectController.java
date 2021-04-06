package com.revature.registry.controller;

import java.util.List;

import com.revature.registry.model.Project;
import com.revature.registry.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/project", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<List<Project>> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project project) {
        return projectService.updateProjectById(id, project);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Project> deleteUser(@PathVariable("id") int id) {
        return projectService.deleteProjectById(id);
    }
}
