package org.glassfish.jersey.examples.managedclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(InternalResource.PATH)
@Produces(MediaType.TEXT_PLAIN)
public class InternalResource {
	public static final String PATH = "internal";
	
	@GET
	@Path("a")
	@HeaderRequire(headerName="custom-header", headerValue="a")
	public String getA() {
		return "A";
	}
	@GET
	@Path("b")
	@HeaderRequire(headerName="custom-header", headerValue="b")
	public String getB() {
		return "B";
	}

}
