package org.glassfish.jersey.examples.managedclient;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ClientBinding;

@ClientBinding(configClass=ClientAConfig.class)
@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
public @interface ClientA { }

abstract class ClientConfigBase extends ClientConfig implements ClientRequestFilter {
	public ClientConfigBase() {
		register(this);
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().putSingle(headerName(), headerValue());
	}
	
	protected abstract String headerValue();
	protected String headerName() {
		return "custom-header";
	}
}

class ClientAConfig extends ClientConfigBase {
	@Override
	protected String headerValue() {
		return "a";
	}
}


@ClientBinding(configClass=ClientBConfig.class)
@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
@interface ClientB { }

class ClientBConfig extends ClientConfigBase {
	@Override
	protected String headerValue() {
		return "b";
	}
}