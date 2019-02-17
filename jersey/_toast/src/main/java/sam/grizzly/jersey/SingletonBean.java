package sam.grizzly.jersey;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Produces(MediaType.TEXT_PLAIN)
@Singleton
@Path(SingletonBean.PATH)
public class SingletonBean {
	public static String[] paths() {
		return new String[] {
				PATH.concat(PATH_COUNT),
				PATH.concat(PATH_URI)
		};
	}
	
	public static final String PATH = "/singleton";
	public static final String PATH_COUNT = "/count";
	public static final String PATH_URI = "/uri";
	
	@Inject
	private Count count;
	
	@Path("count")
	@GET
	public String count() {
		return Integer.toString(count.incrementAndGet());
	}
	@Path("uri")
	@GET
	public String uri(@Context UriInfo uriInfo) {
		return uriInfo.getPath()+"\n"+uriInfo+"\n"+uriInfo.getClass();
	}
}
