package sam.jersey.app;

import org.glassfish.jersey.examples.xmlmoxy.CustomerResource;
import org.glassfish.jersey.examples.xmlmoxy.beans.Address;
import org.glassfish.jersey.examples.xmlmoxy.beans.Customer;
import org.glassfish.jersey.examples.xmlmoxy.beans.PhoneNumber;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(CustomerResource.class);
			
		// register(new MoxyJsonFeature());
		register(new MoxyXmlFeature(moxyClasses()));
	}

	@SuppressWarnings("rawtypes")
	public static Class[] moxyClasses() {
		return new Class[]{Customer.class, Address.class, PhoneNumber.class};
	}

}
