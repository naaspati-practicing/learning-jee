package org.glassfish.jersey.examples.entityfiltering.resource;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;

import org.glassfish.jersey.examples.entityfiltering.domain.EntityStore;
import org.glassfish.jersey.examples.entityfiltering.domain.Task;
import org.glassfish.jersey.examples.entityfiltering.domain.Task;
import org.glassfish.jersey.examples.entityfiltering.filtering.TaskDetailedView;

@Path(TasksResource.PATH)
public class TasksResource implements BaseResource<Task> {
	public static final String PATH = "tasks";
	
	public static final String[] paths() {
		return new String[]{
			PATH,
			PATH.concat("?detailed=true"),
			PATH.concat("/detailed"),
			
			PATH.concat("/1"),
			PATH.concat("/1?detailed=true")
		};
	}

	@Inject
	EntityStore entityStore;

	@Override
	public Task getUnit(long id) {
		return entityStore.getTask(id);
	}
	
	@GET
	@Path("detailed")
	@TaskDetailedView
	public List<Task> getDetailedTasks() {
		return entityStore.getTasks();
	}
	@Override
	public GenericEntity<List<Task>> getAll() {
		return new GenericEntity<List<Task>>(entityStore.getTasks()){};
	}
	@Override
	public Annotation detailedAnnotation() {
		return TaskDetailedView.Factory.newInstance();
	}
}
