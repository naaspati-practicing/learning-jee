import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.ContentType;
import org.glassfish.grizzly.http.util.MimeType;

//FIXME complete me
public class Main extends HttpHandler {
	public static final String HOST = "localhost";
	public static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.createSimpleServer();
		server.getServerConfiguration().addHttpHandler(new Main(), "/", "/time");
		
		server.start();
		try {
			waitForExit();
		} finally {
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

	@Override
	public void service(Request req, Response res) throws Exception {
		String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		res.setContentLength(time.length());
		res.setContentType("text/plain");
		res.getWriter().write(time);
	}
}
