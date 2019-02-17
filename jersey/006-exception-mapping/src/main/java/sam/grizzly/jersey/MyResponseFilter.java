package sam.grizzly.jersey;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
public class MyResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext rc)
			throws IOException {
        System.out.println("MyResponseFilter.postFilter() enter");
        rc.setEntity(rc.getEntity() + ":" + getClass().getSimpleName(), null, MediaType.TEXT_PLAIN_TYPE);
        System.out.println("MyResponseFilter.postFilter() exit");
	}
}
