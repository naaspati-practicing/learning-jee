package org.glassfish.jersey.examples.entityfiltering.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.examples.entityfiltering.filtering.ProjectDetailedView;

@XmlRootElement
public class Project {
	private long id;
	private String name;
	private String description;
	
	@ProjectDetailedView
	private List<Task> tasks;
	
	@ProjectDetailedView
	private List<User> users;
	
	public Project() {}

	public Project(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
