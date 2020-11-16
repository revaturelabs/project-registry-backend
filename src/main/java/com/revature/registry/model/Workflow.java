package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Workflow {
	@Id
	@GeneratedValue
	private int id;
	private String description;
	

	@ManyToOne
	@JoinColumn(name = "belongs_to")
	private Account account;
	

}
