package sam.grizzly.jersey.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionMapper<E extends MyException> implements ExceptionMapper<E> {

	@Override
	public Response toResponse(E e) {
		Response r = e.getResponse();
		return Response.status(r.getStatus())
				.entity("Code:" + r.getStatus() + ":" + getClass().getSimpleName())
				.build();
	}
}
