package org.glassfish.jersey.examples.sysprops.impl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.examples.sysprops.PropertyResource;

public class PropertyResourceImpl implements PropertyResource {
	private UriInfo uri;
	private String name;
	
	public PropertyResourceImpl(UriInfo uri, String name) {
		this.uri = uri;
		this.name = name;
	}

	@Override
	public String get() {
		String value = System.getProperty(name);
		if(value == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		return value;
	}

	@Override
	public String set(String value) {
		if (System.setProperty(name, value) == null) 
            throw new WebApplicationException(Response.created(uri.getRequestUri()).entity(value).build());
        return value;
	}

}
