package org.glassfish.jersey.examples.entityfiltering.security.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.examples.entityfiltering.security.domain.RestrictedEntity;

@Path(UnrestrictedResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
public class UnrestrictedResource {
	public static final String PATH = "unrestricted-resource";
	
	@GET
    public RestrictedEntity getRestrictedEntity() {
        return RestrictedEntity.dummyInstance();
    }
	
}
