package org.glassfish.jersey.examples.freemarker.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

import sam.jersey.utils.ANSI;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class FreemarkerResource {
	private static final Logger LOGGER = Logger.getLogger(FreemarkerResource.class.getName());
	
	@GET
	@Path("simple-hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleHello () {
		return "simple-hello";
	}
	
	@GET
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Path("hello")
	public Viewable getHello () {
		LOGGER.info(ANSI.red("here"));
		Map model = new HashMap();
		model.put("user", "Pavel");
		model.put("items", new ArrayList<>(Arrays.asList("item1","item2","item3")));
		
		return new Viewable("/hello.ftl", model);
	}
	
	@GET
	@Path("hello-default-model")
	public Viewable getHelloDefaultModel () {
		return new Viewable("/hello-default-model.ftl", "Pavel");
	}
	
	@GET	
	@Path("autoTemplate")
	public Viewable getAutoTemplate() {
		Map<String, String> model = Collections.singletonMap("user", "Pavel");
		return new Viewable("/org/glassfish/jersey/examples/freemarker/resources/FreemarkerResource.ftl", model);
	}
	
	@GET
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Path("hello-without-suffix")
	public Viewable getHelloWitoutSuffix () {
		Map model = new HashMap();
		model.put("user", "Pavel");
		model.put("items", Arrays.asList("item1","item2","item3"));
		
		return new Viewable("/hello", model);
	}
}
