package org.glassfish.jersey.examples.managedclient;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class CustomHeaderFeature implements DynamicFeature {
	
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		final HeaderRequire require = resourceInfo.getResourceMethod().getAnnotation(HeaderRequire.class);
		if(require != null)
			context.register(new CustomHeaderFilter(require.headerName(), require.headerValue()));
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
@interface HeaderRequire {
	String headerName();
	String headerValue();
}