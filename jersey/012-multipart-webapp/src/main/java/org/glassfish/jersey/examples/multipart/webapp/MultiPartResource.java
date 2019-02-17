package org.glassfish.jersey.examples.multipart.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("form")
public class MultiPartResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello world";
	}
	
	@POST
	@Path("part")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String post(@FormDataParam("part") String s) {
		return s;
	}
	
	@POST
	@Path("part-file-name")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String post(
			@FormDataParam("part") String s,
			@FormDataParam("part") FormDataContentDisposition sd
			) {
		return s + ":" + sd.getFileName();
	}
	
	@POST
	@Path("xml-jaxb-part")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String post(
			@FormDataParam("part") String s,
			@FormDataParam("part") FormDataContentDisposition sd,
			
			@FormDataParam("bean") Bean b,
			@FormDataParam("bean") FormDataContentDisposition bd
			
			) {
		return s + ":" + sd.getFileName() + "," + b.getValue() + ":" + bd.getFileName();
	}
	
	
	
	

}
