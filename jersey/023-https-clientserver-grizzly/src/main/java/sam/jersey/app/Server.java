package sam.jersey.app;

import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.examples.httpsclientservergrizzly.SSLFactory;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class Server {
	private final HttpServer server;

	public Server(boolean start) throws URISyntaxException {
		this.server = GrizzlyHttpServerFactory.createHttpServer(
				new URI(App.BASE_URI),        // uri 
				new App(),                    // config
				true,                         // secure
				SSLFactory.createSSLEngine(), // ssl engine 
				start                        // start_server
				);
	}

	public void shutdownNow() {
		if(server != null)
			server.shutdownNow();
	}
	
	
}
