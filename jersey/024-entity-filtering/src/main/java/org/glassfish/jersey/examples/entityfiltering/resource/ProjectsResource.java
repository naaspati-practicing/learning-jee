package org.glassfish.jersey.examples.entityfiltering.resource;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;

import org.glassfish.jersey.examples.entityfiltering.domain.EntityStore;
import org.glassfish.jersey.examples.entityfiltering.domain.Project;
import org.glassfish.jersey.examples.entityfiltering.filtering.ProjectDetailedView;

@Path(ProjectsResource.PATH)
public class ProjectsResource implements BaseResource<Project> {
	public static final String PATH = "/projects";
	
	public static final String[] paths() {
		return new String[]{
			PATH,
			PATH.concat("?detailed=true"),
			PATH.concat("/detailed"),
			
			PATH.concat("/1"),
			PATH.concat("/1?detailed=true"),
			PATH.concat("/detailed/1"),
		};
	}

	@Inject
	EntityStore entityStore;

	@Override
	public Project getUnit(long id) {
		return entityStore.getProject(id);
	}
	
	@GET
    @Path("/detailed")
    @ProjectDetailedView
    public List<Project> getDetailedProjects() {
        return getAll().getEntity();
    }
	
	@GET
    @Path("/detailed/{id}")
    @ProjectDetailedView
    public Project getDetailedProject(@PathParam("id") final Long id) {
        return getUnit(id);
    }

	@Override
	public GenericEntity<List<Project>> getAll() {
		return new GenericEntity<List<Project>>(entityStore.getProjects()){};
	}

	@Override
	public Annotation detailedAnnotation() {
		return ProjectDetailedView.Factory.newInstance();
	}
}
