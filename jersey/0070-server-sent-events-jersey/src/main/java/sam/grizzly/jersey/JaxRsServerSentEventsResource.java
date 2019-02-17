package sam.grizzly.jersey;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("server-sent-events")
public class JaxRsServerSentEventsResource {
	private static volatile SseEventSink eventSink = null;
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getMessageQueue(@Context SseEventSink sink) {
		eventSink = sink;
	}
	
	@POST
	public void addMessage(
			String message, 
			@Context Sse sse
			) {
		SseEventSink sink = eventSink;
		if(sink != null) {
			sink.send(sse.newEvent("custom-message", message));
		}
	}
	
	@DELETE
	public void close() {
		SseEventSink sink = eventSink;
		
		if(sink != null) 
			sink.close();
		sink = null;
	}
	
	@POST
	@Path("domains/{id}")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void startDomain(
			@PathParam("id") int id, 
			@Context SseEventSink domainSink,
			@Context Sse sse
			) {
		
		String[] msgs = {
				"starting domain " + id + " ...",
				"50%",
				"60%",
				"70%",
				"99%",
				"done"
		};
		
		new Thread(() -> {
			try {
				for (String s : msgs) {
					domainSink.send(sse.newEvent("domain-progress", s));
					Thread.sleep(200);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	

}
