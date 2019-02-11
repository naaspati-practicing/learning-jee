package javaeetutorial.web.dukeetf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/dukeetf", asyncSupported=true)
public class DukeETFServlet extends HttpServlet {
	private static final long serialVersionUID = -1917837546473064932L;

	private static final Logger LOGGER = Logger.getLogger(DukeETFServlet.class.getSimpleName());
	private Queue<AsyncContext> requestQueue;
	@EJB
	private PriceVolumeBean pvbean;

	@Override
	public void init(ServletConfig config) throws ServletException {
		requestQueue = new ConcurrentLinkedQueue<>();
		pvbean.register(this);
		super.init(config);
		LOGGER.fine(() -> "INIT: "+getClass());
	}
	
	public void send(double price, int volume) {
		for (AsyncContext ac : requestQueue) {
			try {
				String msg = String.format("{\"price\": %.2f, \"volume\": %d}", price, volume);
				PrintWriter pw = ac.getResponse().getWriter();
				pw.append(msg);
				LOGGER.info("Sent: "+msg);
				ac.complete();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "failed to send", e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		AsyncContext ac = req.startAsync();

		ac.addListener(new AsyncListener() {
			void log(boolean remove) {
				LOGGER.info(System.currentTimeMillis()+": "+ Thread.currentThread().getStackTrace()[2].toString());
				if(remove)
					requestQueue.remove(ac);
			}
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				log(true);
			}
			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				log(false);
			}
			@Override
			public void onError(AsyncEvent event) throws IOException {
				log(true);
			}
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				log(true);
			}
		});

		requestQueue.add(ac);
		LOGGER.info("Connection Open.");
	}
}
