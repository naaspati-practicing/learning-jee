package sam.jersey.app;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import sam.grizzly.jersey.Count;
import sam.grizzly.jersey.Echo;
import sam.grizzly.jersey.Hello;
import sam.grizzly.jersey.HelloListWriter;
import sam.grizzly.jersey.HelloWriter;
import sam.grizzly.jersey.SingletonBean;

public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(Echo.class,
				Hello.class, 
				SingletonBean.class,
				HelloListWriter.class,
				HelloWriter.class
				);
		
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(Count.class).to(Count.class).in(Singleton.class);
			}
		});
	}

}
