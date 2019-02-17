package org.glassfish.jersey.examples.jersey_ejb.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.examples.jersey_ejb.entities.Message;

@Stateless
public class MessageBoardResourceBean {
	@Context private UriInfo uri;
	@EJB private MessageHolderSingletonBean singleton;
	
	@GET
	public List<Message> getMessages() {
		return singleton.getMessages();
	}
	
	@POST
	public Message addMessage(String msg) {
		return singleton.addMessage(msg);
	}
	@GET
	@Path("{msgID}")
	public Message getMessage(@PathParam("msgID") int uniqueId) {
		Message m = singleton.getMessage(uniqueId);
		if(m == null)
			throw new NotFoundException();
		return m;
	}
	@Path("{msgID}")
	@DELETE
	public void deleteMessage(@PathParam("msgID") int uniqueId) {
		if(!singleton.deleteMessage(uniqueId))
			throw new NotFoundException();
	}
}
