package sam.grizzly.jersey.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException exception) {
	       Response r = exception.getResponse();
           return Response.status(r.getStatus()).entity("Code:" + r.getStatus() + ":"
                   + getClass().getSimpleName()).build();
	}

}
