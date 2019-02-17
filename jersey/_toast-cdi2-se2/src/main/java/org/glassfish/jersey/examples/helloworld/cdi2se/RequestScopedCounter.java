package org.glassfish.jersey.examples.helloworld.cdi2se;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopedCounter {
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public RequestScopedCounter() {}
	
	public int get() {
		return counter.get();
	}
	public int incrementAndGet() {
		return counter.incrementAndGet();
	}
}
