package sam.grizzly.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class Echo {
	
	@GET 
	public String get() {
		return "GET: ";
	}
	@POST 
	public String post(String s) {
		return "POST: "+s;
	}
}
