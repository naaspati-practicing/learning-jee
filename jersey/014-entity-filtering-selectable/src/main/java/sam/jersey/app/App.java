package sam.jersey.app;

import org.glassfish.jersey.examples.entityfiltering.selectable.domain.Address;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.Person;
import org.glassfish.jersey.examples.entityfiltering.selectable.domain.PhoneNumber;
import org.glassfish.jersey.examples.entityfiltering.selectable.resource.PersonResource;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Entity Data Filtering application using request parameters.
 *
 * @author Andy Pemberton (pembertona at gmail.com)
 * @author Michal Gajdos
 */

public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(PersonResource.class);
		
		register(SelectableEntityFilteringFeature.class);
		property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "select");
		register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
	}
	
	@SuppressWarnings("rawtypes")
	public static Class[] moxyClasses() {
		return new Class[]{Person.class, Address.class, PhoneNumber.class};
	}

}
