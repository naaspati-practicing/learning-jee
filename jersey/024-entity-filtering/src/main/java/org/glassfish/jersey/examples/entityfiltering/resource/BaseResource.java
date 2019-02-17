package org.glassfish.jersey.examples.entityfiltering.resource;

import java.lang.annotation.Annotation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public interface BaseResource<E> {
	
	@GET
	@Path("hello")
	public default String hello() {
		return "hello: "+getClass();
	}
	
	@GET 
	default Response getAll(@QueryParam("detailed") boolean detailed)  {
		return Response.ok()
				.entity(getAll(), detailedAnnotationArray(detailed))
				.build();
	}

	@GET
	@Path("{id}")
	default Response getUnit(@PathParam("id") long id, @QueryParam("detailed") boolean detailed) {
		return Response.ok()
		.entity(getUnit(id), detailedAnnotationArray(detailed))
		.build();
	}

	default Annotation[] detailedAnnotationArray(boolean detailed) {
		Annotation[] ants = new Annotation[detailed ? 1 : 0];
		if(ants.length == 1)
			ants[0] = detailedAnnotation();
		
		return ants;
	}

	E getUnit(long id);
	@SuppressWarnings("rawtypes")
	GenericEntity getAll();
	Annotation detailedAnnotation();
}
