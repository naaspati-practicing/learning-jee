package sam.jersey.app;

import java.net.URI;
import java.util.logging.Logger;

import org.glassfish.jersey.examples.server.async.BlockingPostChatResource;
import org.glassfish.jersey.examples.server.async.FireAndForgetChatResource;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;
import org.glassfish.jersey.server.ResourceConfig;

public class App extends ResourceConfig {
	public static final URI BASE_URI = URI.create("http://localhost:8080/rest/");
	
    public static final String ASYNC_MESSAGING_FIRE_N_FORGET_PATH = "async/messaging/fireAndForget";
    public static final String ASYNC_MESSAGING_BLOCKING_PATH = "async/messaging/blocking";
    public static final String ASYNC_LONG_RUNNING_OP_PATH = "async/longrunning";


    public App() {
    	super(
    			BlockingPostChatResource.class,
    			FireAndForgetChatResource.class
    			) ;
    	
    	registerInstances(new LoggingFeature(Logger.getLogger(App.class.getName()), Verbosity.PAYLOAD_ANY));
	}
    
}
