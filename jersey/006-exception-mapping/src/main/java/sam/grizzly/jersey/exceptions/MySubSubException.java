package sam.grizzly.jersey.exceptions;

import javax.ws.rs.core.Response;

public class MySubSubException extends MySubException {
	private static final long serialVersionUID = -3158720723133674367L;

	public MySubSubException(Response r) {
		super(r);
	}

}
