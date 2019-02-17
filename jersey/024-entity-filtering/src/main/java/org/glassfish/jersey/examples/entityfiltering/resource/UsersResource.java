package org.glassfish.jersey.examples.entityfiltering.resource;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;

import org.glassfish.jersey.examples.entityfiltering.domain.EntityStore;
import org.glassfish.jersey.examples.entityfiltering.domain.User;
import org.glassfish.jersey.examples.entityfiltering.filtering.UserDetailedView;

@Path(UsersResource.PATH)
public class UsersResource implements BaseResource<User> {
	public static final String PATH = "users";
	
	public static final String[] paths() {
		return new String[]{
			PATH,
			PATH.concat("?detailed=true"),
			
			PATH.concat("/1"),
			PATH.concat("/1?detailed=true"),
		};
	}

	@Inject
	EntityStore entityStore;

	@Override
	public User getUnit(long id) {
		return entityStore.getUser(id);
	}

	@Override
	public GenericEntity<List<User>> getAll() {
		return new GenericEntity<List<User>>(entityStore.getUsers()){};
	}
	@Override
	public Annotation detailedAnnotation() {
		return UserDetailedView.Factory.newInstance();
	}
}
