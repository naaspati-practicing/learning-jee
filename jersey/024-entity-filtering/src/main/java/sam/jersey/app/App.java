package sam.jersey.app;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.examples.entityfiltering.domain.EntityStore;
import org.glassfish.jersey.examples.entityfiltering.resource.ProjectsResource;
import org.glassfish.jersey.examples.entityfiltering.resource.TasksResource;
import org.glassfish.jersey.examples.entityfiltering.resource.UsersResource;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";

	public App() {
		super(
				UsersResource.class,
				TasksResource.class, 
				ProjectsResource.class
				);

		/**
		 * 
		 * \MEGAsync\_unzipped\JAVA\JAVA_EE\jersey\examples\17-entity-filtering
		 * 
		 * not working
		 * moxy json throwing cycle exceptin 
		 */
		if(10 < System.currentTimeMillis())
			throw new IllegalAccessError();

		register(EntityFilteringFeature.class);
		register(new MoxyJsonConfig().setFormattedOutput(true).resolver());

		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(EntityStore.class).to(EntityStore.class).in(Singleton.class);
			}
		});
	}

}
