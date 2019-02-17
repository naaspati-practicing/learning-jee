package sam.jetty.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.examples.freemarker.resources.FreemarkerResource;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

@ApplicationPath("/rest/*")
public class App extends ResourceConfig {
	public App() {
		super(FreemarkerResource.class);
		
		register(LoggingFeature.class);
		register(FreemarkerMvcFeature.class);
		property(MvcFeature.TEMPLATE_BASE_PATH, "freemarker");
	}
}
