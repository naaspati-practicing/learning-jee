package sam.jersey.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.examples.monitoring.MyExceptionMapper;
import org.glassfish.jersey.examples.monitoring.MyResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/rest/*")
public class App extends ResourceConfig {
	public static final String BASE_URI = "http://localhost:8080/";
	
	public App() {
		super(MyExceptionMapper.class, MyResource.class);
		setApplicationName("MonitoringExample");
		property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);
	}
}
