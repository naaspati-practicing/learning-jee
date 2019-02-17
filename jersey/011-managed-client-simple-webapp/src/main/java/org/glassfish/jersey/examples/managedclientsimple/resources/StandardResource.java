package org.glassfish.jersey.examples.managedclientsimple.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("resource")
public class StandardResource {
	@GET
	@Path("dog")
	public String dog() { 
		return "mashiro";
	}
	@GET
	@Path("cat")
	public String cat() { 
		return "Poos in boots";
	}
	@GET
	@Path("elefant")
	public String elefant() { 
		return "Jumbo";
	}
	@GET
	@Path("car/{id}")
	public String car(@PathParam("id") String id) { 
		return "Car[id = "+id+"]";
	}
	

}
