package org.glassfish.jersey.examples.multipart.webapp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest/")
public class App extends ResourceConfig {
	public App() {
		super(MultiPartFieldInjectedResource.class, MultiPartResource.class, MultiPartFeature.class);
	}
}
