package sam.jersey.app;

import org.glassfish.jersey.examples.managedclient.ClientA;
import org.glassfish.jersey.examples.managedclient.InternalResource;
import org.glassfish.jersey.examples.managedclient.PublicResource;
import org.glassfish.jersey.server.ResourceConfig;


public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(PublicResource.class, InternalResource.class);
		property(ClientA.class.getName().concat(".baseUri"), BASE_URI.concat(InternalResource.PATH));
	}
}
