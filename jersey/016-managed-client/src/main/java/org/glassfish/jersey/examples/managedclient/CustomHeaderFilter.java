package org.glassfish.jersey.examples.managedclient;

import java.io.IOException;
import java.util.Objects;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CustomHeaderFilter implements ContainerRequestFilter {
	private final String headerName;
	private final String headerValue;

	public CustomHeaderFilter(String headerName, String headerValue) {
		this.headerName = Objects.requireNonNull(headerName);
		this.headerValue = Objects.requireNonNull(headerValue);
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(!headerValue.equals(requestContext.getHeaderString(headerName))) {
			requestContext.abortWith(
					Response.status(Status.BAD_REQUEST)
					.entity(new BadRequestException(String.format("Expected header '%s' not present or value not equal to '%s'", headerName, headerValue)))
					.build()
					);
		}
	}

}
