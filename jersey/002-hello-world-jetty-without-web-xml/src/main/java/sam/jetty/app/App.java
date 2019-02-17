package sam.jetty.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class App extends ResourceConfig {
	public App() {
		super(HelloWorld.class);
	}

}
