package com.revature.registry.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Project {
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	@ManyToOne
	@JoinColumn(name="stack")
	private Stack stack;
	private String description;
//	
	@ManyToOne
	@JoinColumn(name="created_by")
	private Account account;
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name = "project_tags_jt", 
		joinColumns = {@JoinColumn(name = "project_id")},
		inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private List<Tag> tags;
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name = "project_account_jt", 
			joinColumns = {@JoinColumn(name = "project_id")},
			inverseJoinColumns = {@JoinColumn(name = "acocount_id")})
		private List<Account> accounts;

}
