package com.revature.registry.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Iteration {

	@Id
	@GeneratedValue
	private int id;
		
	private int iterationCount;
	private LocalDate start_date;
	private LocalDate end_date;

	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
}
