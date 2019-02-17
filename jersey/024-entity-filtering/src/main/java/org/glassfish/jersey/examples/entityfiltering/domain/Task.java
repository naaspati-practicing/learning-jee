package org.glassfish.jersey.examples.entityfiltering.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.examples.entityfiltering.filtering.TaskDetailedView;

@XmlRootElement
public class Task {
	private long id;
	private String name;
	private String description;
	
	@TaskDetailedView
	private Project project;
	
	@TaskDetailedView
	private User user;
	
	public Task() { }

    public Task(final Long id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
