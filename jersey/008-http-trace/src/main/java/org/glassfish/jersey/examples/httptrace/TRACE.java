package org.glassfish.jersey.examples.httptrace;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.HttpMethod;

@HttpMethod(TRACE.NAME)
@Retention(RUNTIME)
@Target({ METHOD })
public @interface TRACE {
	public static final String NAME = "TRACE"; 

}
