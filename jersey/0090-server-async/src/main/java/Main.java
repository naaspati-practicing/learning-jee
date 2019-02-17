

import java.net.URISyntaxException;
import java.util.Scanner;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import sam.jersey.app.App;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Main {
	
    public static void main(String[] args) throws URISyntaxException {
		HttpServer server = null;
		try {
			server = GrizzlyHttpServerFactory.createHttpServer(App.BASE_URI, new App(), true);
			waitForExit();
		} finally {
			if(server != null)
				server.shutdownNow();
		}
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
