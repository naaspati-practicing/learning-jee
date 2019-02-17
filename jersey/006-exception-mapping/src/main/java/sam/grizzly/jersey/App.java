package sam.grizzly.jersey;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import sam.grizzly.jersey.exceptions.MyException;
import sam.grizzly.jersey.exceptions.MySubException;
import sam.grizzly.jersey.exceptions.MySubSubException;
//UN-TESTED
@Path("exception")
@Produces(TEXT_PLAIN)
@Consumes(TEXT_PLAIN)
public class App {
	
	@GET
	public String ping() {
		return "ping! ";
	}
	
	@POST
	@Path("webapp_entity")
	public String testWebApplicationExceptionEntity(String s) {
		System.out.println("webapp_entity");
		throw new WebApplicationException(Response.status(status(s)).entity(s).build());
	}
	private int status(String s) {
		return Integer.parseInt(s.substring(s.indexOf(':')+1));
	}

	@POST
	@Path("webapp_no_entity")
	public String testWebApplicationExceptionNoEntity(String s) {
		throw new WebApplicationException(Response.status(status(s)).build());
	}
	@POST
	@Path("my")
	public String testMyException(String s) {
		throw new MyException(Response.status(status(s)).build());
	}
	@POST
    @Path("mysub")
    public String testMySubException(String s) {
        throw new MySubException(Response.status(status(s)).build());
    }
	@POST
    @Path("mysubsub")
    public String testMySubSubException(String s) {
        throw new MySubSubException(Response.status(status(s)).build());
    }
	@POST
    @Path("request_exception")
    public String exceptionInRequestFilter() {
        throw new InternalServerErrorException();        // should not reach here
    }
	@GET
    @Path("response_exception")
    public String exceptionInResponseFilter() {
        return "Response Exception";
    }
}
