package sam.jetty.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	@Inject HelloMessage msg;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return msg.toString();
	}
	
	@GET
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(@PathParam("name") String name) {
		msg.setName(name);
		return msg.toString();
	}
}
