package sam.grizzly.jersey;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	private int count;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello stranger";
	}
	@Path("count")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String count() {
		return Integer.toString(count++);
	}
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject hello(@PathParam("name") String name) {
		return new Obj(name, System.currentTimeMillis()).toJsonObject();
	}
}
