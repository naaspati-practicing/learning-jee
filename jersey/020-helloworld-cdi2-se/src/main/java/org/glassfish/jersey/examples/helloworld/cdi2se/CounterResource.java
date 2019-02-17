package org.glassfish.jersey.examples.helloworld.cdi2se;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(CounterResource.PATH)
@Produces(MediaType.TEXT_PLAIN)
public class CounterResource {
	public static String[] paths() {
		return new String[]{
				PATH+"/"+PATH_APPLICATION,
				PATH+"/"+PATH_REQUEST
				};
	}
	
	public static final String PATH = "counter";
	public static final String PATH_APPLICATION = "application";
	public static final String PATH_REQUEST = "request";
	
	@Inject
	private RequestScopedCounter rcounter;
	@Inject
	private ApplicationScopedCounter acounter;
	
	@GET
    @Path(PATH_APPLICATION)
    public String getAppCounter() {
        return Integer.toString(acounter.incrementAndGet());
    }

    @GET
    @Path(PATH_REQUEST)
    public String getReqCounter() {
        return Integer.toString(rcounter.incrementAndGet());
    }
}
