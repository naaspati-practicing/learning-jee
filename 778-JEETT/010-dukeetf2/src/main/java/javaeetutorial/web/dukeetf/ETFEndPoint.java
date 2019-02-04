package javaeetutorial.web.dukeetf;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/dukeetf")
public class ETFEndPoint {
	private static final Logger LOGGER = Logger.getLogger(ETFEndPoint.class.getSimpleName());
	private static final Queue<Session> queue = new ConcurrentLinkedQueue<>();
	
	public static void send(double price, int volume) {
		queue.forEach(ses -> {
			try {
				String msg = String.format("{\"price\": %.2f, \"volume\": %d}", price, volume);
				ses.getBasicRemote().sendText(msg);
				LOGGER.info("Sent: "+msg);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "failed to send", e);
			}	
		});
	}
	
	@OnOpen
	public void open(Session session) {
		queue.add(session);
		LOGGER.fine(() -> "Open Connection@"+session.getId());
	}
	@OnClose
	public void close(Session session) {
		queue.remove(session);
		LOGGER.fine(() -> "Close Connection@"+session.getId());
	}
	@OnError
	public void error(Session session, Throwable t) {
		queue.remove(session);
		LOGGER.log(Level.SEVERE, "failed session: "+session.getId(), t);
	}
}
