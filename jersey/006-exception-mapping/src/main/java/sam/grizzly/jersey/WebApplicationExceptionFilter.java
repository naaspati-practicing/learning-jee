package sam.grizzly.jersey;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;

@Provider
public class WebApplicationExceptionFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        System.out.println("WebApplicationExceptionFilter.preFilter() enter");

        String path = ((ContainerRequest) context).getRequestUri().getPath();
        if (path.endsWith("request_exception") && context.hasEntity() && ((ContainerRequest) context)
                .readEntity(String.class).equals("Request Exception")) {
            throw new WebApplicationException(Response.Status.OK);
        }
        System.out.println("WebApplicationExceptionFilter.preFilter() exit");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("WebApplicationExceptionFilter.postFilter() enter");
        if (responseContext.hasEntity() && responseContext.getEntity().equals("Response Exception")) {
            throw new WebApplicationException(Response.Status.OK);
        }
        System.out.println("WebApplicationExceptionFilter.postFilter() exit");
    }
}
