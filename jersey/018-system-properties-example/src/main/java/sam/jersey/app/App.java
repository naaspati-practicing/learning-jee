package sam.jersey.app;

import org.glassfish.jersey.examples.sysprops.PropertiesReader;
import org.glassfish.jersey.examples.sysprops.PropertiesWriter;
import org.glassfish.jersey.examples.sysprops.impl.PropertyNamesResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(
				PropertyNamesResourceImpl.class,
				PropertiesWriter.class,
				PropertiesReader.class
				);
	}

}
