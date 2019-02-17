package sam.jetty.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloWorld {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		return "Hello World! (002-hello-world-jetty-without-web-xml)";
	}

}