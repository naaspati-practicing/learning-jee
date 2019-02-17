package sam.grizzly.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {
	private int count;
	@Inject
	private Count count_singleton;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello stranger";
	}
	@Path("count")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String count() {
		return "\ncurrent: "+count+"\nsingleton: "+count_singleton.incrementAndGet();
	}
	@GET
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(@PathParam("name") String name) {
		return "hello "+name+"!";
	}
	
	@Path("exception")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String exception(@Context Response response) {
		throw new NotFoundException(response);
	}
}
