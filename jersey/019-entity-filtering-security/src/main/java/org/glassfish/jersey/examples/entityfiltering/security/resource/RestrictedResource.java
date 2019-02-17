package org.glassfish.jersey.examples.entityfiltering.security.resource;

import java.lang.annotation.Annotation;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.examples.entityfiltering.security.domain.RestrictedEntity;
import org.glassfish.jersey.examples.entityfiltering.security.domain.Roles;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.message.filtering.SecurityAnnotations;

@Path(RestrictedResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
public class RestrictedResource {
	
	public static final String PATH = "restricted-resource";
	public static final String PATH_DENY_ALL = "denyAll";
	public static final String PATH_PERMIT_ALL = "permitAll";
	public static final String PATH_ROLES_ALLOWED = "rolesAllowed";
	public static final String PATH_RUNTIME_ROLES_ALLOWED = "runtimeRolesAllowed";
	
	@GET
	@Path(PATH_DENY_ALL)
	@DenyAll
	public RestrictedEntity denyAll() {
		return RestrictedEntity.dummyInstance();
	}
	@GET
	@Path(PATH_PERMIT_ALL)
	@PermitAll 
	public RestrictedEntity permitAll() {
		return RestrictedEntity.dummyInstance();
	}
	
	@GET
	@Path(PATH_ROLES_ALLOWED)
	@RolesAllowed(Roles.MANAGER) 
	public RestrictedEntity rolesAllowed() {
		return RestrictedEntity.dummyInstance();
	}
	
	@GET
	@Path(PATH_RUNTIME_ROLES_ALLOWED)
	public Response runtimeRolesAllowed(@QueryParam("roles") String roles) {
		return Response.ok()
				.entity(RestrictedEntity.dummyInstance(), new Annotation[]{SecurityAnnotations.rolesAllowed(Tokenizer.tokenize(roles == null ? "" : roles))})
				.build() ;
	}
	
	
	
	
}
