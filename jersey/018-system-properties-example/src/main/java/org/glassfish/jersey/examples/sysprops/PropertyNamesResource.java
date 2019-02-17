package org.glassfish.jersey.examples.sysprops;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("properties")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public interface PropertyNamesResource {
	 @GET Set<String> getPropertyNames();
	 @Path("{name}") PropertyResource getProperty(@PathParam("name") String name);
}
