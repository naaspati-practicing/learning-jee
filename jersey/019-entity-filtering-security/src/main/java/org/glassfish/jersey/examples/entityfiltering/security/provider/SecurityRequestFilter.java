package org.glassfish.jersey.examples.entityfiltering.security.provider;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.examples.entityfiltering.security.domain.Roles;

@Provider
@PreMatching
public class SecurityRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		ctx.setSecurityContext(new SecurityContext() {
			@Override
			public boolean isUserInRole(String role) {
				return Roles.MANAGER.equals(role);
			}
			@Override
			public boolean isSecure() {
				return false;
			}
			@Override
			public Principal getUserPrincipal() {
				return new Principal() {
					@Override
					public String getName() {
						return "Jersey";
					}
				};
			}
			@Override
			public String getAuthenticationScheme() {
				return null;
			}
		});
	}

}
