package org.glassfish.jersey.examples.managedclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.Uri;

@Path("public")
@Produces(MediaType.TEXT_PLAIN)
public class PublicResource {
	
	@Uri("a") // resolves to <base>/internal/a
	@ClientA
	private WebTarget target;
	
	@GET
	@Path("a")
	public String getTargetA() {
		return target.request(MediaType.TEXT_PLAIN).get(String.class);
	}
	@GET
	@Path("b")
	public Response getTargetA(@Uri("internal/b") @ClientB WebTarget target) {
		return target.request(MediaType.TEXT_PLAIN).get();
	}
}
