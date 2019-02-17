package org.glassfish.jersey.examples.server.async.managed;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

@Path(SimpleJerseyExecutorManagedLongRunningResource.PATH)
@Produces(MediaType.TEXT_PLAIN)
public class SimpleJerseyExecutorManagedLongRunningResource {
	public static final String PATH = "managedasync/longrunning";
	public static final long DELAY = Optional.ofNullable(System.getProperty("DELAY")).map(Long::parseLong).orElse(1000L);
	
	@GET
	@ManagedAsync
	public void longWait(@Suspended AsyncResponse ar, @QueryParam("id") int requestId) {
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ar.resume(createMessage(requestId));
	}

	public static String createMessage(int requestId) {
		return requestId+" - Hello World!";
	}

}
