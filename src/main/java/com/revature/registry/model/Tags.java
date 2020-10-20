package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.Data;

@Entity
@Data
public class Tags {
	@GeneratedValue
	private int id;
	
	private String name;
	private String description;

}
