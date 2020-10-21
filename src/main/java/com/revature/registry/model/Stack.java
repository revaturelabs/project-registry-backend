package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Stack {
	@Id
	@GeneratedValue
	private int id;
	
	private String name;	

}
