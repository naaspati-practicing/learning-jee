package sam.grizzly.jersey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Singleton
@Path("/singleton")
public class SingletonBean {
	
	@Inject
	private Count count;
	
	@Path("count")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String count() {
		return Integer.toString(count.incrementAndGet());
	}
	@Path("uri")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String uri(@Context UriInfo uriInfo) {
		return uriInfo.getPath()+"\n"+uriInfo+"\n"+uriInfo.getClass();
	}
}
