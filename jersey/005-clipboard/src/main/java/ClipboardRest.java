import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

@Path("clipboard")
public class ClipboardRest {
	@Inject private Store store;
	@Context private  Request request;
	private static final List<Variant> VARIANTS = Variant.mediaTypes(APPLICATION_JSON_TYPE, TEXT_PLAIN_TYPE).add().build();

	@GET
	@Produces({APPLICATION_JSON})
	public Response content() {
		if(store.isCurrentEmpty())
			return Response.noContent().build();
		return Response.ok(store.getCurrent(), request.selectVariant(VARIANTS).getMediaType()).build();
	}
	
	@PUT
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public ClipboardData put(ClipboardData content) {
		store.put(content);
		return content;
	}

	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public ClipboardData append(ClipboardData data) {
		if(store.getCurrent() == null) { 
			store.put(data);
			return data;
		} else {
			store.getCurrent().append(data);
			return store.getCurrent();
		}
	}

	@DELETE
	public void delete(@QueryParam("id") int id) {
		store.remove(id);
	}
	
	@GET
	@Path("all")
	@Produces(APPLICATION_JSON)
	public Collection<ClipboardData> all() {
		return store.getData().values();
	}
	
	@DELETE
	@Path("all")
	public void deleteAll() {
		store.clear();
	}
}
