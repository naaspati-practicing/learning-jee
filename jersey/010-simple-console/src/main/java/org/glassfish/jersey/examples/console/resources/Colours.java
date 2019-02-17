package org.glassfish.jersey.examples.console.resources;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.codehaus.jettison.json.JSONArray;

public class Colours {
	private static final String[] colors = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};
	
	@Context 
	HttpHeaders headers;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getColorsAsPlainText(@QueryParam("match") String filter) {
		return compute(filter, () -> String.join("\n", colors), Collectors.joining("\n"));
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getColorsAsJson(@QueryParam("match") String filter) {
		return compute(filter, () -> new JSONArray(Arrays.<String>asList(colors)), Collectors.collectingAndThen(Collectors.toList(), JSONArray::new));
	}
	private <E> E compute(String filter, Supplier<E> ifFilterEmpty, Collector<CharSequence, ?, E> collector) {
		if(filter == null || filter.trim().isEmpty())
			return ifFilterEmpty.get();
		
		return Arrays.stream(colors).filter(c -> c.contains(filter)).collect(collector);
	}

}
