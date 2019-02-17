package sam.grizzly.jersey;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;

public class Process implements Runnable {
	private static final AtomicInteger counter = new AtomicInteger(0);
	private final int id;
	private final CountDownLatch latch;
	private final SseBroadcaster broadcaster;
	private final Sse sse;
	
	public Process(int testRecievers, Sse sse) {
		this.sse = sse;
		this.broadcaster = sse.newBroadcaster();
		id = counter.incrementAndGet();
		latch = testRecievers > 0 ? new CountDownLatch(testRecievers) : null;
	}
	
	public int getId() {
		return id;
	}
	public SseBroadcaster getBroadcaster() {
		return broadcaster;
	}
	void release() {
		if(latch != null)
			latch.countDown(); // wait for all test EventSources to be registered
		
		broadcast("starting domain " + id + " ...");
		broadcast("50%");
		broadcast("60%");
		broadcast("70%");
		broadcast("99%");
		broadcast("done");
	}
	
	private static final String NAME = "domain-progress";
	private void broadcast(String data) {
		broadcaster.broadcast(sse.newEvent(NAME, data));
	}

	@Override
	public void run() {
		try {
		if(latch != null)
			latch.await(5, TimeUnit.SECONDS);
		
		// TODO Auto-generated method stub
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
