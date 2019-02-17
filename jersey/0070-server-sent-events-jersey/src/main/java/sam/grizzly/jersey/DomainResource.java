package sam.grizzly.jersey;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("domain")
public class DomainResource {
	private static final Map<Integer, Process> processes = new ConcurrentHashMap<>();
	
	@Path("start")
	@POST
	public Response start(
			@DefaultValue("0") @QueryParam("testSources") int testSources,
			@Context Sse sse
			) {
		Process process = new Process(testSources, sse);
		processes.put(process.getId(), process);
		
		Executors.newSingleThreadExecutor().execute(process);
		URI uri = UriBuilder.fromResource(DomainResource.class).path("path/{id}").build(process.getId());
		return Response.created(uri).build();
	}
	
	@Path("process/{id}")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@GET
	public void getProcess(
			@PathParam("id") int id,
			@DefaultValue("false") @QueryParam("testSource") boolean testSource,
			@Context SseEventSink sink
			) {
		
		Process process = processes.get(id);
		if(process != null) {
			if(testSource)
				process.release();
			process.getBroadcaster().register(sink);
		} else {
			throw new NotFoundException();
		}
	}
}
