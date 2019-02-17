package org.glassfish.jersey.examples.httpsclientservergrizzly;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.Objects;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@javax.ws.rs.ext.Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter {
	private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getName());

	@Inject
	private Provider<UriInfo> uri;

	private static final String REALM = "HTTPS Example authentication";

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		User user = authenticate(ctx);
		ctx.setSecurityContext(new Authorizer(user));
	}

	private User authenticate(ContainerRequestContext ctx) throws AuthenticationException {
		String auth = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(auth == null)
			throw new AuthenticationException("Authentication credentials are required: ", REALM);

		if (!auth.startsWith("Basic ")) {
			return null;
			// additional checks should be done here
			// "Only HTTP Basic authentication is supported"
		}

		auth = auth.substring("Basic ".length());
		String parsed  = new String(Base64.getDecoder().decode(auth), StandardCharsets.US_ASCII);

		int index = parsed.indexOf(':');
		if(index < 0) // "Invalid syntax for username and password"
			throw new WebApplicationException(Status.BAD_REQUEST);

		String username = parsed.substring(0, index);
		String password = parsed.substring(index+1);

		User user = null;
		if("user".equals(username) && "password".equals(password)) {
			user = new User(username, REALM);
			LOGGER.info("USER AUTHENTICATED: "+user);
		} else {
			System.out.println("USER NOT AUTHENTICATED");
            throw new AuthenticationException("Invalid username or password\r\n", REALM);
		}
		return user;
	}

	public class Authorizer implements SecurityContext {
		private User user;
		private Principal principal;

		public Authorizer(User user) {
			this.user = user;
			this.principal = new Principal() {
				@Override
				public String getName() {
					return user.username;
				}
			};
		}

		@Override
		public Principal getUserPrincipal() {
			return principal;
		}

		@Override
		public boolean isUserInRole(String role) {
			return Objects.equals(role, user.role);
		}

		@Override
		public boolean isSecure() {
			return "https".equals(uri.get().getRequestUri().getScheme());
		}
		@Override
		public String getAuthenticationScheme() {
			return SecurityContext.BASIC_AUTH;
		}
	}

	public static class User {
		private String username;
		private String role;

		public User(String username, String role) {
			this.username = username;
			this.role = role;
		}

		@Override
		public String toString() {
			return "User [username=" + username + ", role=" + role + "]";
		}
	}

}
