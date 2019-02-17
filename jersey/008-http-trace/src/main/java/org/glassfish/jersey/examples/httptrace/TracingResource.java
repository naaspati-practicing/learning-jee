package org.glassfish.jersey.examples.httptrace;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.glassfish.jersey.server.ContainerRequest;
import org.json.JSONException;

@Path(Utils.ROOT_PATH_ANNOTATED)
public class TracingResource {
	
	@Context Request req;

	@TRACE
	@Produces(MediaType.APPLICATION_JSON)
	public String trace() throws JSONException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return Utils.toJsonString((ContainerRequest) req);
	}
}
