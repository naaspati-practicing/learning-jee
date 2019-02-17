package org.glassfish.jersey.examples.helloworld.cdi2se;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Singleton
@Path("hello")
public class HelloWorldResource {
	public static final String PATH = "hello";
	
	public static String[] paths() {
		return new String[]{ PATH+"/sameer" };
	}
	
	@Inject
	private HelloBean helloBean;

    @GET
    @Path("{name}")
    @Produces("text/plain")
    public String getHello(@PathParam("name") String name) {
        return helloBean.hello(name);
    }

}
