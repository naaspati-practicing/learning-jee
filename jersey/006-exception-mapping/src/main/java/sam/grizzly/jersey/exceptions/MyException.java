package sam.grizzly.jersey.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class MyException extends RuntimeException {
	
	private static final long serialVersionUID = 2027490922840614111L;
	private final Response response;
	
	public MyException(Response r) {
		this.response = r;
	}
	public Response getResponse() {
		return response;
	}
}
