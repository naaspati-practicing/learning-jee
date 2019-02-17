package org.glassfish.jersey.examples.httpsclientservergrizzly;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class AuthenticationException extends WebApplicationException {
	private static final long serialVersionUID = -3092924351768902233L;
	private final String realm;
	
	public AuthenticationException(String msg, String realm) {
		super(msg, Status.UNAUTHORIZED);
		this.realm = realm;
	}
	public String getRealm() {
		return realm;
	}
}
