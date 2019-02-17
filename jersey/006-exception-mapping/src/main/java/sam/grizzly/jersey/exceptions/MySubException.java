package sam.grizzly.jersey.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class MySubException extends MyException {
	private static final long serialVersionUID = 2870805427577591196L;

	public MySubException(Response r) {
		super(r);
	}

}
