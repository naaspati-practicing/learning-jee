package sam.jetty.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.examples.managedbeans.resources.ManagedBeanException;
import org.glassfish.jersey.examples.managedbeans.resources.ManagedBeanExceptionMapper;
import org.glassfish.jersey.examples.managedbeans.resources.ManagedBeanPerRequestResource;
import org.glassfish.jersey.examples.managedbeans.resources.ManagedBeanSingletonResource;

@ApplicationPath(App.PATH)
public class App extends Application {
	public static final String PATH = "/managedbean";
	
	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<>(Arrays.asList(
				ManagedBeanExceptionMapper.class,
				ManagedBeanPerRequestResource.class,
				ManagedBeanSingletonResource.class
				));
	}
}
