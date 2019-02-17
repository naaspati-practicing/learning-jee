package org.glassfish.jersey.examples.monitoring;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MyExceptionMapper implements ExceptionMapper<MyException>{

	@Override
	public Response toResponse(MyException exception) {
		return Response.ok("mapped: "+exception).build();
	}
	

}
