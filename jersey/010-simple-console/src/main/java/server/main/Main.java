package server.main;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.examples.console.resources.FormResource;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
	
    public static void main(String[] args) throws URISyntaxException {
		HttpServer server = null;
		try {
			server = GrizzlyHttpServerFactory.createHttpServer(new URI(Utils.SERVER_URI), config(), true);
			waitForExit();
		} finally {
			if(server != null)
				server.shutdownNow();
		}
	}
	public static ResourceConfig config() {
		ResourceConfig config = new ResourceConfig( 
				FormResource.class,
				JettisonFeature.class,
				RenderableWriter.class
				);
		
		// config.registerInstances(new LoggingFeature(Logger.getLogger(Main.class.getSimpleName()), Verbosity.PAYLOAD_ANY));
		return config;
	}
	private static void waitForExit() {
		System.out.println("type --exit to exit....");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			if(sc.nextLine().trim().equalsIgnoreCase("--exit"))
				break;
		}
	}
}
