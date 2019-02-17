package org.glassfish.jersey.examples.managedbeans.resources;

import javax.annotation.ManagedBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/per-request")
@ManagedBean
public class ManagedBeanPerRequestResource {
	@Context private UriInfo uri;
	@QueryParam("x") private String x;
	
	public static class MyIntercepter {
		
		@AroundInvoke
		public Object arround(InvocationContext ctx) throws Exception {
			return String.format("INTERCEPTED: %s", ctx.proceed());
		}
	}
	
    @GET
    @Produces("text/plain")
    @Interceptors(MyIntercepter.class)
    public String getMessage() {
        return String.format("echo from %s: %s", uri.getPath(), x);
    }

    @Path("exception")
    public String getException() {
        throw new ManagedBeanException();
    }
}
