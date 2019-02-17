package org.glassfish.jersey.examples.server.async;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler;

import sam.jersey.app.App;
import sam.jersey.app.Utils;

/**
 * Example of a simple blocking point-to-point messaging resource.
 *
 * This version of the messaging resource blocks when POSTing a new message until
 * the message is retrieved.
 *
 * @author Marek Potociar (marek.potociar at oracle.com)
 */

@Path(App.ASYNC_MESSAGING_BLOCKING_PATH)
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@Singleton
public class BlockingPostChatResource {
	private static final AtomicBoolean initiated = new AtomicBoolean(false);
	private Logger logger;
	private ExecutorService ex;
	private BlockingQueue<String> messages;
	
	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String hello() {
		return Utils.asJson("message","hello", "from", getClass(), "time", LocalDateTime.now(), "thread-pool", ex).toString();
	}
	
	@PostConstruct
	public void init() {
		if(!initiated.compareAndSet(false, true))
			throw new IllegalStateException();
		
		logger = Logger.getLogger(getClass().getSimpleName());
		ex = Executors.newCachedThreadPool(
				new ThreadFactoryBuilder()
				.setNameFormat(logger.getName()+"-%d")
				.setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler())
				.build()
				);
		messages = new ArrayBlockingQueue<>(5);
	}
	
	@GET
	public void pickUpMessage(@Suspended final AsyncResponse res, @QueryParam("id") String messageId) {
		logger.fine(() -> "Received GET "+Utils.asJson("messageId", messageId, "thread-name", Thread.currentThread().getName(), "context", res).toString(4));
		
		ex.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String message = messages.take();
					logger.fine(() -> "Resuming GET "+ Utils.asJson("messageId", messageId, "thread-name", Thread.currentThread().getName(), "context", res).toString(4));
					res.resume(message);
				} catch (InterruptedException e) {
					logger.log(Level.SEVERE, "Waiting for a message pick-up interrupted. Cancelling context" + res.toString(), ex);
					res.cancel(); // close the open connection
				}
			}
		});
	}
	
	public static final String POST_NOTIFICATION_RESPONSE = "Message stored.";
	
	@POST
	public void postMessage(@Suspended final AsyncResponse res, String message) {
		logger.fine(() -> "Received POST "+Utils.asJson("message", message, "thread-name", Thread.currentThread().getName(), "context", res).toString(4));
		
		ex.execute(new Runnable() {
			@Override
			public void run() {
				try {
					messages.put(message);
					logger.fine(() -> "POSTed message (successfully queued)"+ Utils.asJson("message", message, "thread-name", Thread.currentThread().getName(), "context", res).toString(4));
					res.resume(POST_NOTIFICATION_RESPONSE);
				} catch (InterruptedException e) {
	                 logger.log(Level.SEVERE, "Waiting for a queueing a message '" + message + "' has been interrupted.", ex);
					res.resume(e); // propagate info about the problem
				}
			}
		});
	}
}
