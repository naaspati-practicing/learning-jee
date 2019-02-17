package org.glassfish.jersey.examples.sysprops.impl;

import java.util.Set;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.examples.sysprops.PropertyNamesResource;
import org.glassfish.jersey.examples.sysprops.PropertyResource;

public class PropertyNamesResourceImpl implements PropertyNamesResource {
	
	@Context
	private UriInfo uri;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<String> getPropertyNames() {
		return (Set) System.getProperties().keySet();
	}
	@Override
	public PropertyResource getProperty(String name) {
		return new PropertyResourceImpl(uri, name);
	}

}
