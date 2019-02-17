package org.glassfish.jersey.examples.helloworld.cdi2se;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloBean {
	public HelloBean() {}
	
	public String hello(String name) {
		return "Hello "+name;
	}

}
