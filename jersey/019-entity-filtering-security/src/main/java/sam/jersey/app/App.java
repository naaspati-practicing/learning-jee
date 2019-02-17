package sam.jersey.app;

import org.glassfish.jersey.examples.entityfiltering.security.resource.RestrictedResource;
import org.glassfish.jersey.examples.entityfiltering.security.resource.UnrestrictedResource;
import org.glassfish.jersey.message.filtering.SecurityEntityFilteringFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

// SecurityEntityFilteringApplication
public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(
				SecurityEntityFilteringFeature.class,
				RestrictedResource.class,
				UnrestrictedResource.class
				);
		
		register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
	}

}
