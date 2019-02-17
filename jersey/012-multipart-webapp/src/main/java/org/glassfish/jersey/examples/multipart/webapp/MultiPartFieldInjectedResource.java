package org.glassfish.jersey.examples.multipart.webapp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("form-field-injected")
public class MultiPartFieldInjectedResource {
	@FormDataParam("string") private String s;
	@FormDataParam("string") private FormDataContentDisposition sd;
	
	@FormDataParam("bean") private Bean b;
	@FormDataParam("bean") private FormDataContentDisposition bd;
	
	@POST
	@Path("xml-jaxb-part")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String post() {
		return s + ":" + sd.getFileName() + "," + b.getValue() + ":" + bd.getFileName();
	}
}
