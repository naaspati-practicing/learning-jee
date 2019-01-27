import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChain;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.http.HttpContent;
import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.http.HttpServerFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.utils.DelayedExecutor;
import org.glassfish.grizzly.utils.IdleTimeoutFilter;

//FIXME complete me
public class Main  extends BaseFilter {
	public static final String HOST = "localhost";
	public static final int PORT = 8080;

	/*
	 * old httpserver, not practicing, grizzly 2.4 provides better HttpServer
	 */
	public static void main(String[] args) throws IOException {
		if(10 < System.currentTimeMillis())
			throw new IllegalAccessError("not yet implmented");
		
		// file:///C:/Users/Sameer/Documents/MEGAsync/websites/grizzly/javaee.github.io/grizzly/httpframework.html

		FilterChain filterChain = FilterChainBuilder.stateless()
				.add(new TransportFilter())
				.add(new IdleTimeoutFilter(new DelayedExecutor(Executors.newCachedThreadPool()), 10, TimeUnit.SECONDS))
				.add(new HttpServerFilter())
				.add(new Main())
				.build();

		TCPNIOTransport transport = TCPNIOTransportBuilder.newInstance().build();
		transport.setProcessor(filterChain);

		try {
			transport.bind(HOST, PORT);
			transport.start();
			System.out.println("type --exit to exit....");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			while(true) {
				if(sc.nextLine().trim().equalsIgnoreCase("--exit"))
					break;
			}
		} finally {
			System.out.println("stopping transport...");
			transport.shutdownNow();
			System.out.println("stopped transport...");
		}
	}




}
