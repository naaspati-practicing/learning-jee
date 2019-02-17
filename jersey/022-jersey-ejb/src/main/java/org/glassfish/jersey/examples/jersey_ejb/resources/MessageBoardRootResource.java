package org.glassfish.jersey.examples.jersey_ejb.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("/")
public class MessageBoardRootResource {
	@EJB
	private MessageBoardResourceBean bean;
	
	@GET
	public String hello() {
		return "hello world!";
	}
	
	@Path("messages")
	public MessageBoardResourceBean getBean() {
		return bean;
	}
}
