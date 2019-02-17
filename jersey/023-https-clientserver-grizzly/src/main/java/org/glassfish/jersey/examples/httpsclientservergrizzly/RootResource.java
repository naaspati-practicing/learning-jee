package org.glassfish.jersey.examples.httpsclientservergrizzly;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

/**
 * Simple resource demonstrating low level approach of getting user credentials.
 *
 * Better way would be injecting {@link javax.ws.rs.core.SecurityContext}.
 *
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */

@Path("/")
public class RootResource {
	private static final Logger LOGGER = Logger.getLogger(RootResource.class.getName());
	public static final String CONTENT = "JERSEY HTTPS EXAMPLE\n";
	
	@GET
	public String get1(@Context HttpHeaders headers) {
		LOGGER.info("GET User: "+getUserName(headers));
		return CONTENT;
	}

	private String getUserName(HttpHeaders headers) {
		// this is a very minimalistic and "naive" code; if you plan to use it
        // add necessary checks (see org.glassfish.jersey.examples.httpsclientservergrizzly.authservergrizzly.SecurityFilter)
		
		String auth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		auth = auth.substring(SecurityContext.BASIC_AUTH.length()+1);
		
		String parsed  = new String(Base64.getDecoder().decode(auth), StandardCharsets.US_ASCII);
		
		int index = parsed.indexOf(':');
		// String username = parsed.substring(0, index);
        // String password = parsed.substring(index+1);
		
		return parsed.substring(0, index);
	}

}
