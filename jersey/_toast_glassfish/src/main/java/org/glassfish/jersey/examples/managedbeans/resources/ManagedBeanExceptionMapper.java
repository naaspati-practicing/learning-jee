package org.glassfish.jersey.examples.managedbeans.resources;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@ManagedBean
public class ManagedBeanExceptionMapper implements ExceptionMapper<ManagedBeanException> {
	@Context private UriInfo uiFieldInject;
	@Context private ResourceContext rc;
	
    private UriInfo uiMethodInject;
    private UriInfo ui;

    @Context
    public void set(UriInfo ui) {
        this.uiMethodInject = ui;
    }
    private void ensureInjected() throws IllegalStateException {
        if (uiFieldInject == null || uiMethodInject == null || rc == null) {
            throw new IllegalStateException();
        }
    }

    @PostConstruct
    public void postConstruct() {
        ensureInjected();
        this.ui = uiMethodInject;
    }

    @Override
    public Response toResponse(ManagedBeanException exception) {
        ensureInjected();
        return Response.serverError().entity(String.format("ManagedBeanException from %s", ui.getPath())).build();
    }
}
