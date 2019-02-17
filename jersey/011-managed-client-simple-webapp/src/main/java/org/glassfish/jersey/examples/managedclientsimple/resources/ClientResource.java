package org.glassfish.jersey.examples.managedclientsimple.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.Uri;

@Path("client")
public class ClientResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello world";
	}

	/**
	 * Make request to external web site using injected client. The response from the injected client is then
	 * returned as a response from this resource method.
	 *
	 * @param webTarget Injected web target.
	 * @return Response.
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("glassfish")
	public Response glassfish(@Uri("http://glassfish.java.net/") WebTarget webTarget) {
		return Response.fromResponse(webTarget.request().get()).build();
	}

	/**
	 * Query {@link StandardResource} and return result based on the results from methods of the {@link StandardResource}.
	 *
	 * @param dogWebTarget Injected client.
	 * @param catWebTarget Injected client.
	 * @param elefantWebTarget Injected client.
	 * @return String entity.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("animals")
	public String animals(
			@Uri("resource/dog") WebTarget dogWebTarget,
			@Uri("resource/cat") WebTarget catWebTarget,
			@Uri("resource/elefant") WebTarget elefantWebTarget
			) {
		return String.format("Queried animals: %s, %s, and %s", parse(dogWebTarget), parse(catWebTarget), parse(elefantWebTarget));
	}

	private String parse(WebTarget target) {
		return target.request().get(String.class);
	}
	
	 /**
     * Query {@link StandardResource} using a injected client. The client injection is using a template parameter {@code id}
     * which is filled by JAX-RS implementation using a path parameter of this resource method.
     *
     * @param webTarget Injected client.
     * @param id Path parameter.
     * @return String entity.
     */
   @GET
   @Produces(MediaType.TEXT_PLAIN)
   @Path("car/{id}")
   public String car(
		   @Uri("resource/car/{id}") WebTarget webTarget,
		   @PathParam("id") String id
		   ) {
	   return "Response from resource/car/"+id+": "+parse(webTarget);
   }

}
