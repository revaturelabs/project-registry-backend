package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Project {
	@GeneratedValue
	private int id;
//	
//	private String name;
//	@ManyToOne
//	@JoinColumn(name="status")
//	private Status status;
//	@ManyToOne
//	@JoinColumn(name="stack")
//	private Stack stack;
//	private String description;
//	
//	@ManyToOne
//	@JoinColumn(name="created_by")
//	private Account account;
	

}
