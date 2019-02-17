package org.glassfish.jersey.examples.server.async.managed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import server.main.Main;

public class ManagedAsyncResourceTest extends JerseyTest {
	final Logger logger = Logger.getLogger(ManagedAsyncResourceTest.class.getSimpleName());

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		return Main.config();
	}

	@Override
	protected void configureClient(ClientConfig config) {
		config.register(new JacksonFeature());
	}
	
	class MethodWrap<E> {
		final boolean sequential;
		final Object sequentialLock = new Object();
		final Map<Integer, E> responses = new ConcurrentHashMap<>();
		final CountDownLatch requestLatch;
		final String method;
		
		public MethodWrap(String method, boolean sequential, int requestLatchCount) {
			this.method = method;
			this.sequential = sequential;
			this.requestLatch = new CountDownLatch(requestLatchCount);
		}
	}

	@Test
	public void testLongRunningResource() throws InterruptedException {
		final WebTarget target = target().path(SimpleJerseyExecutorManagedLongRunningResource.PATH);

		int MAX_MESSAGES  = 100;
		int LATCH_WAIT_TIMEOUT = 10 * getAsyncTimeoutMultiplier();
		boolean debugMode = false;
		MethodWrap<String> get = new MethodWrap<>("GET", false, MAX_MESSAGES);

		ExecutorService ex = Executors.newCachedThreadPool(
				new ThreadFactoryBuilder()
				.setNameFormat("async-resource-test-%d")
				.setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler())
				.build()
				);
		
		Map<Integer, String> expected = new HashMap<>();
		
		try {
			for (int i = 0; i < MAX_MESSAGES; i++) {
				int requestId = i;
				expected.put(requestId, SimpleJerseyExecutorManagedLongRunningResource.createMessage(i));
				
				process(i, ex, debugMode, get, 
						() -> target.queryParam("id", requestId).request().get(String.class),
						"Error sending GET request"
						);
			}
			
			if(debugMode)
				get.requestLatch.await();
			else {
				if (!get.requestLatch.await(LATCH_WAIT_TIMEOUT, TimeUnit.SECONDS)) 
                    logger.log(Level.SEVERE, "Waiting for all GET requests to complete has timed out.");
			}
		} finally {
			ex.shutdownNow();
		}
		
		logger.info(toString(get, "GET responses received: "));
		
		assertEquals(MAX_MESSAGES, get.responses.size());
		expected.forEach((s,t) ->assertEquals("requestId: "+s, t, get.responses.get(s)));
	}

	private <E> void process(int requestId, ExecutorService ex, boolean debugMode, MethodWrap<E> method, Callable<E> task, String errorMessage) {
		ex.execute(new Runnable() {
			@Override
			public void run() {
				if(debugMode || method.sequential) {
					synchronized (method.sequentialLock) {
						invoke();
					}
				} else {
					invoke();
				}
			}
			private void invoke() {
				try {
					int attemptCounter = 0;
					while(true) {
						attemptCounter++;
						try {
							method.responses.put(requestId, task.call());
							break;
						} catch (Throwable e) {
							logger.log(Level.SEVERE, String.format(errorMessage+" <%s> for %d. time.", requestId, attemptCounter), e);
						}
						if (attemptCounter > 3) 
                            break;
						
                        Thread.sleep(10);
					}
				} catch (InterruptedException e) {
					logger.log(Level.WARNING, String.format("Error sending GET message <%s>: Interrupted", requestId), e);
				} finally {
					method.requestLatch.countDown();
				}
			}
		});
	}
	
	@Test
	public void testChatResource() throws InterruptedException {
		final WebTarget target = target().path(ChatResources.PATH);

		int MAX_MESSAGES  = 100;
		int LATCH_WAIT_TIMEOUT = 10 * getAsyncTimeoutMultiplier();
		boolean debugMode = false;
		MethodWrap<Message> get = new MethodWrap<>("GET",false, MAX_MESSAGES);
		MethodWrap<Integer> post = new MethodWrap<>("POST", false, MAX_MESSAGES);

		ExecutorService ex = Executors.newCachedThreadPool(
				new ThreadFactoryBuilder()
				.setNameFormat("async-resource-test-%d")
				.setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler())
				.build()
				);
		
		Map<Integer, String> expected = new HashMap<>();
		
		try {
			for (int i = 0; i < MAX_MESSAGES; i++) {
				expected.put(i, SimpleJerseyExecutorManagedLongRunningResource.createMessage(i));
				int requestId = i;
				process(requestId, ex, debugMode, post,
						() -> target.request().post(Entity.json(new Message(String.valueOf(requestId), String.valueOf(requestId)))).getStatus(), 
						"Error POSTING message");
				
				process(requestId, ex, debugMode, get,
						() -> target.request(MediaType.APPLICATION_JSON).<Message>get(Message.class), 
						"Error sending GET request");
			}
			
			if(debugMode) {
				post.requestLatch.await();
				get.requestLatch.await();
			} else {
				if (!post.requestLatch.await(LATCH_WAIT_TIMEOUT, TimeUnit.SECONDS)) 
                    logger.log(Level.SEVERE, "Waiting for all POST requests to complete has timed out.");
				if (!get.requestLatch.await(LATCH_WAIT_TIMEOUT, TimeUnit.SECONDS)) 
                    logger.log(Level.SEVERE, "Waiting for all GET requests to complete has timed out.");
			}
		} finally {
			ex.shutdownNow();
		}
		
		logger.info(toString(post, "POST responses received: "));
		logger.info(toString(get, "GET responses received: "));
		
		post.responses.forEach((requestId, status) -> assertEquals("requestId: "+requestId, 200, status.intValue()));
		
		StringBuilder sb = new StringBuilder("Lost Messages\n");
		int len = sb.length();
		
		IntStream.range(0, MAX_MESSAGES)
		.filter(i -> !get.responses.containsKey(i))
		.forEach(id -> sb.append("  ").append(new Message(String.valueOf(id), String.valueOf(id))).append('\n'));
		
		if(sb.length() != len)
			fail(sb.append('\n').toString());
		
		assertEquals(MAX_MESSAGES, post.responses.size());
        assertEquals(MAX_MESSAGES, get.responses.size());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String toString(MethodWrap method, String title) {
		StringBuilder sb = new StringBuilder(title)
				.append(method.responses.size())
				.append('\n');
		
		method.responses.forEach((s,t) -> sb.append(s).append(": ").append(t).append('\n'));
		
		return sb.toString();
	}


}
