import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.filterchain.FilterChain;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.http.HttpClientFilter;
import org.glassfish.grizzly.impl.FutureImpl;
import org.glassfish.grizzly.impl.SafeFutureImpl;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;

public class Main {
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws URISyntaxException, IOException {
		final ClientDownloadFilter downloader = new ClientDownloadFilter();

		FilterChain fc = FilterChainBuilder.stateless()
				.add(new TransportFilter())
				.add(new HttpClientFilter())
				.add(downloader)
				.build();

		TCPNIOTransport transport = TCPNIOTransportBuilder.newInstance().build();
		transport.setProcessor(fc);

		try {
			transport.start();
			Scanner scanner = new Scanner(System.in);
			System.out.println("A simple downloader");
			System.out.println("type --exit to exit");
			while(true) {
				Connection<Result> connection = null;
				System.out.print("enter url: ");
				String url = scanner.nextLine().trim();
				if(url.isEmpty()) continue;
				if(url.equalsIgnoreCase("--exit"))
					break;

				try {
					final URI uri = new URI(url);

					final String host = uri.getHost();
					final int port = uri.getPort() > 0 ? uri.getPort() : 80;
					
					final FutureImpl<Result> completeFuture = SafeFutureImpl.create();
					downloader.setTask(uri, url, completeFuture);
					connection = transport.connect(host, port).get(10, TimeUnit.SECONDS);
					
					Result result = completeFuture.get();
					System.out.println("result: "+result);
				} catch (InterruptedException | ExecutionException|URISyntaxException | TimeoutException e) {
					if(e instanceof InterruptedException)
						break;
					if(e instanceof URISyntaxException)
						System.out.println("bad url: "+url);
					else
						System.out.println("connection failed: "+url);
					e.printStackTrace(System.out);
					System.out.println();
				} finally {
					if(connection != null)
						connection.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Stopping transport...");
			transport.shutdownNow();
			System.out.println("Stopped transport...");
		}

	}

}
