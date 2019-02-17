package sam.grizzly.jersey;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

@Singleton
public class Count {
	private final AtomicInteger count = new AtomicInteger();

	public final int incrementAndGet() {
		return count.incrementAndGet();
	}
	public final int get() {
		return count.get();
	}
	

}
