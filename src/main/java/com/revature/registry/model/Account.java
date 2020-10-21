package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Account {

	@Id
	@GeneratedValue
	private int id;

	private String username;

	@ManyToOne
	@JoinColumn(name = "role")
	private Role role;
	
//	@ManyToOne
//	private ProjectIteration projectIteration;
}