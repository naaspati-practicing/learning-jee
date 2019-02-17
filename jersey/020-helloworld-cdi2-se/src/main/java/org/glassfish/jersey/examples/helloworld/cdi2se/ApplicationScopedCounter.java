package org.glassfish.jersey.examples.helloworld.cdi2se;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationScopedCounter {
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public ApplicationScopedCounter() {}
	
	public int get() {
		return counter.get();
	}
	public int incrementAndGet() {
		return counter.incrementAndGet();
	}
}
