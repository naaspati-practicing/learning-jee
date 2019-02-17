package org.glassfish.jersey.examples.managedbeans.resources;

import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/singleton")
@Singleton
@ManagedBean
public class ManagedBeanSingletonResource {
	private static final Logger LOGGER = Logger.getLogger(ManagedBeanSingletonResource.class.getSimpleName());
	
	@Resource(name="injectedResource")
	private int counter;
	
	@Context
	private UriInfo uri;
	
	private EntityManager entityManager;
	
	@PersistenceUnit(unitName="MainPU")
	void setEntityManager(EntityManagerFactory factory) {
		this.entityManager = factory.createEntityManager();
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		return "hello visitor: ".concat(Integer.toString(counter++));
	}
	@PUT
	public void reset(int count) {
		this.counter = count;
	}
    @Path("exception")
    public String exception() {
        throw new ManagedBeanException();
    }
    private Widget find(int id) {
		return entityManager.find(Widget.class, id);
	}
    
    @Path("widget/{id: \\d+}")
    @GET
    public String getWidget(@PathParam("id") int id) {
    	Widget d = find(id);
    	
    	if(d == null)
    		throw new NotFoundException();
    	
    	LOGGER.fine(() -> "GET : "+d);
    	return  d.getVal();
    }
    
    @Path("widget_new")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String setWidget(String val) {
    	Widget w = new Widget(val);
    	entityManager.persist(w);	
    	return w.toString();
    }
    
	@Path("widget/{id: \\d+}")
    @PUT
    public void setWidget(@PathParam("id") int id, String val) {
    	Widget exist = find(id);
    	if(exist == null)
    		throw new NotFoundException("no Widget not found for id: "+id);
    
    	exist.setVal(val);
    	entityManager.merge(exist);	
    }
    
    @Path("widget/{id: \\d+}")
    @DELETE
    public void deleteWidget(@PathParam("id") int id) {
    	entityManager.remove(find(id));
    }
    
    @PreDestroy
    void close() {
    	entityManager.close();
    	LOGGER.fine(() -> "entity manager closed");
    }
}
