package sam.grizzly.jersey;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Hello.PATH)
@Produces(MediaType.TEXT_PLAIN)
public class Hello {
	public static String[] paths() {
		return new String[] {
				PATH,
				PATH.concat(PATH_LIST),
				PATH.concat("/sameer"),
				PATH.concat(PATH_COUNT),
				PATH.concat(PATH_EXCEPTION)
		};
	}
	
	
	public static final String PATH = "/hello";
	public static final String PATH_LIST = "/list";
	public static final String PATH_COUNT = "/count";
	public static final String PATH_EXCEPTION = "/exception";
	
	private int count;
	@Inject
	private Count count_singleton;
	
	@GET
	@Path(PATH_LIST)
	public List<HelloMessage> hello1() {
		return Stream.of("hello stranger", "hello stranger-2", "hello stranger-3").map(HelloMessage::new).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}
	
	@GET
	public HelloMessage hello() {
		return HelloMessage.DEFAULT_MESSAGE;
	}
	@Path(PATH_COUNT)
	@GET
	public String count() {
		return "\ncurrent: "+count+"\nsingleton: "+count_singleton.incrementAndGet();
	}
	@GET
	@Path("{name}")
	public HelloMessage hello(@PathParam("name") String name) {
		return new HelloMessage(name);
	}
	
	@Path(PATH_EXCEPTION)
	@GET
	public HelloMessage exception(@Context Response response) {
		throw new NotFoundException(response);
	}
}
