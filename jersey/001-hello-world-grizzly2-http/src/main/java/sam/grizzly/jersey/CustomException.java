package sam.grizzly.jersey;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class CustomException extends WebApplicationException {
	private static final long serialVersionUID = -2250319992987193463L;
	
	public CustomException() {
		super(Response.status(Status.FORBIDDEN)
				.entity("error")
				.type(MediaType.TEXT_PLAIN)
				.build());
	}

}
