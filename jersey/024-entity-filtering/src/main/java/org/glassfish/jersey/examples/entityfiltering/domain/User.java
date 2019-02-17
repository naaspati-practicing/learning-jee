package org.glassfish.jersey.examples.entityfiltering.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private long id;
	private String name;
	private String email;
	
	private List<Project> projects;
	private List<Task> tasks;
	
	public User() { }

    public User(final Long id, final String name, final String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
