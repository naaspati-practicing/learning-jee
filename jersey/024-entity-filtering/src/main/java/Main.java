import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.examples.entityfiltering.resource.ProjectsResource;
import org.glassfish.jersey.examples.entityfiltering.resource.TasksResource;
import org.glassfish.jersey.examples.entityfiltering.resource.UsersResource;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import sam.jersey.app.App;
import sam.jersey.utils.ANSI;
import sam.jersey.utils.Utils;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Main {
	private static String TITLE = "Jersey Entity Data Filtering Example.";
	
    public static void main(String[] args) throws URISyntaxException {
		HttpServer server = null;
		try {
			server = GrizzlyHttpServerFactory.createHttpServer(new URI(App.BASE_URI), new App(), true);
			waitForExit(
					UsersResource.paths(),
					TasksResource.paths(),
					ProjectsResource.paths()
					);
		} finally {
			if(server != null)
				server.shutdownNow();
		}
	}
    
	private static void waitForExit(String[]... suburls) {
		if(Utils.isEmpty(suburls))
			System.out.println(ANSI.yellow("application started at: ")+App.BASE_URI+"\n"+ANSI.red("type --exit to exit...."));
		else {
			StringBuilder sb = new StringBuilder();
			if(TITLE != null)
				ANSI.yellow(TITLE, sb).append('\n');
			TITLE = null;
			
			ANSI.cyan("Application started.\nTry out one of these URIs:", sb).append('\n');
			String base = App.BASE_URI;
			if(base.charAt(base.length() - 1) != '/')
				base = base.concat("/");

			for (String[] s0 : suburls) {
				for (String s : s0) {
					sb.append("  ");
					ANSI.yellow(App.BASE_URI, sb);
					ANSI.yellow(s.charAt(0) == '/' ? s.substring(1) : s, sb);
					sb.append('\n');	
				}
				sb.append('\n');
			}

			sb.append('\n');
			ANSI.red("type --exit to exit....", sb);
			
			System.out.println(sb);
			sb = null;
		}

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			if(sc.nextLine().trim().equalsIgnoreCase("--exit"))
				break;
		}
	}

	private Main() {}
    
}
