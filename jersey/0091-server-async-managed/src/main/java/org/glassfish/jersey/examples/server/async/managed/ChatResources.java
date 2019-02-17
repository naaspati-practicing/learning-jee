package org.glassfish.jersey.examples.server.async.managed;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

@Path(ChatResources.PATH)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResources {
	public static final String PATH = "chat";
	
	private static final BlockingQueue<AsyncResponse> suspended = new LinkedBlockingQueue<>(5);
	
	@GET
	@ManagedAsync
	public void getMessage(@Suspended AsyncResponse response) throws InterruptedException {
		suspended.put(response);
	}
	
	@POST
	@ManagedAsync
	public String postMessage(Message message) throws InterruptedException {
		suspended.take().resume(message);
		return "Sent!";
	}
}
