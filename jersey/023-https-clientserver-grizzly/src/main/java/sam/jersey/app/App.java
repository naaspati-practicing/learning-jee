package sam.jersey.app;

import org.glassfish.jersey.examples.httpsclientservergrizzly.RootResource;
import org.glassfish.jersey.examples.httpsclientservergrizzly.SecurityFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {
	public static final String BASE_URI = "https://localhost:8080/";
	
	public App() {
		super(RootResource.class,
			SecurityFilter.class
				);
	}

}
