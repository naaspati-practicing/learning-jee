package org.glassfish.jersey.examples.server.async;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import sam.jersey.app.App;

public class ManagedAsyncResourceTest extends JerseyTest {
	final Logger logger = Logger.getLogger(ManagedAsyncResourceTest.class.getSimpleName());

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		return new App();
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
    public void testFireAndForgetChatResource() throws InterruptedException {
        executeChatTest(target().path(App.ASYNC_MESSAGING_FIRE_N_FORGET_PATH),
                FireAndForgetChatResource.POST_NOTIFICATION_RESPONSE);
    }

    @Test
    public void testBlockingPostChatResource() throws InterruptedException {
        executeChatTest(target().path(App.ASYNC_MESSAGING_BLOCKING_PATH), BlockingPostChatResource.POST_NOTIFICATION_RESPONSE);
    }
	public void executeChatTest(WebTarget resourceTarget, String expectedPostMessage) throws InterruptedException {

		int MAX_MESSAGES  = 100;
		int LATCH_WAIT_TIMEOUT = 10 * getAsyncTimeoutMultiplier();
		boolean debugMode = false;
		MethodWrap<String> get = new MethodWrap<>("GET",false, MAX_MESSAGES);
		MethodWrap<String> post = new MethodWrap<>("POST", false, MAX_MESSAGES);

		ExecutorService ex = Executors.newCachedThreadPool(
				new ThreadFactoryBuilder()
				.setNameFormat("async-resource-test-%d")
				.setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler())
				.build()
				);
		
		try {
			for (int i = 0; i < MAX_MESSAGES; i++) {
				// expected.put(i, SimpleJerseyExecutorManagedLongRunningResource.createMessage(i));
				int requestId = i;
				process(requestId, ex, debugMode, post,
						() -> resourceTarget.request().post(Entity.text(String.valueOf(requestId)), String.class), 
						"Error POSTING message");
				
				process(requestId, ex, debugMode, get,
						() -> resourceTarget.queryParam("id", requestId).request().get(String.class), 
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
		
		post.responses.forEach((requestId, response) -> assertEquals("requestId: "+requestId, expectedPostMessage, response));
		
		StringBuilder sb = new StringBuilder("Lost Messages\n");
		int len = sb.length();
		
		IntStream.range(0, MAX_MESSAGES)
		.filter(i -> !get.responses.containsKey(i))
		.forEach(id -> sb.append("  ").append("id: ").append(id).append(", msg: ").append(id).append('\n'));
		
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
