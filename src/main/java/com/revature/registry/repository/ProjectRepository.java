package com.revature.registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.registry.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
