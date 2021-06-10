package com.revature.registry.repository;

import java.util.List;

import com.revature.registry.model.Project;
import com.revature.registry.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    public List<Project> getProjectByStatus(Status s);

}
