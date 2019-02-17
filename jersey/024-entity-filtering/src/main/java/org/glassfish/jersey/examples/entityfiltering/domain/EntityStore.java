package org.glassfish.jersey.examples.entityfiltering.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class EntityStore {
	public static final AtomicBoolean _init = new AtomicBoolean(false);
	
	private final Map<Long, Project> projects = new LinkedHashMap<>();
	private final Map<Long, Task> tasks = new LinkedHashMap<>();
	private final Map<Long, User> users = new LinkedHashMap<>();
	
	@PostConstruct
	void init() {
		if(_init.get())
			throw new IllegalStateException();
		if(!_init.compareAndSet(false, true))
			throw new IllegalStateException();
		   // Projects.
        final Project project = createProject("Jersey", "Jersey is the open source (under dual CDDL+GPL license) JAX-RS 2.0 "
                + "(JSR 339) production quality Reference Implementation for building RESTful Web services.");

        // Users.
        final User robot = createUser("Jersey Robot", "very@secret.com");

        // Tasks.
        final Task filtering = createTask("ENT_FLT", "Entity Data Filtering");
        final Task oauth = createTask("OAUTH", "OAuth 1 + 2");

        // Project -> Users, Tasks.
        add(project, robot);
        filtering.setProject(project);
        oauth.setProject(project);

        // Users -> Projects, Tasks.
        add(robot, project);
        filtering.setUser(robot);
        oauth.setUser(robot);

        // Tasks -> Projects, Users.
        add(filtering, project);
        add(oauth, project);
        add(filtering, robot);
        add(oauth, robot);
    }
	
	protected <E> List<E> newList() {
		return new ArrayList<E>();
	}
    public void add(final Project project, final User user) {
        user.getProjects().add(project);
    }

    public void add(final User user, final Project project) {
        project.getUsers().add(user);
    }

    public void add(final Task task, final User user) {
        user.getTasks().add(task);
    }

    public void add(final Task task, final Project project) {
        project.getTasks().add(task);
    }

    public Project createProject(final String name, final String description) {
        return createProject(name, description, null, null);
    }

    public Project createProject(final String name, final String description, final List<User> users,
                                        final List<Task> tasks) {
        final Project project = new Project(projects.size() + 1L, name, description);

        project.setTasks(tasks == null ? newList() : tasks);
        project.setUsers(users == null ? newList() : users);
        projects.put(project.getId(), project);

        return project;
    }

    public User createUser(final String name, final String email) {
        return createUser(name, email, null, null);
    }

    public User createUser(final String name, final String email, final List<Project> projects, final List<Task> tasks) {
        final User user = new User(users.size() + 1L, name, email);

        user.setProjects(projects == null ? newList() : projects);
        user.setTasks(tasks == null ? newList() : tasks);
        users.put(user.getId(), user);

        return user;
    }

    public Task createTask(final String name, final String description) {
        return createTask(name, description, null, null);
    }

    public Task createTask(final String name, final String description, final Project project, final User user) {
        final Task task = new Task(tasks.size() + 1L, name, description);

        task.setProject(project);
        task.setUser(user);
        tasks.put(task.getId(), task);

        return task;
    }

    public Project getProject(final Long id) {
        return projects.get(id);
    }

    public User getUser(final Long id) {
        return users.get(id);
    }

    public Task getTask(final Long id) {
        return tasks.get(id);
    }

    public List<Project> getProjects() {
    	List<Project> list = newList();
    	list.addAll(projects.values());
        return list;
    }

    public List<User> getUsers() {
    	List<User> list = newList();
    	list.addAll(users.values());
        return list;
    }
    public List<Task> getTasks() {
    	List<Task> list = newList();
    	list.addAll(tasks.values());
        return list;
    }
}
