package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.Data;

@Data
@Entity
public class Stack {
	@GeneratedValue
	private int id;
	
	private String name;	

}
