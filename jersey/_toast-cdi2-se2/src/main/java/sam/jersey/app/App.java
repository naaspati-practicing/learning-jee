package sam.jersey.app;

import org.glassfish.jersey.examples.helloworld.cdi2se.CounterResource;
import org.glassfish.jersey.examples.helloworld.cdi2se.HelloWorldResource;
import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(HelloWorldResource.class, CounterResource.class);
	}
}
