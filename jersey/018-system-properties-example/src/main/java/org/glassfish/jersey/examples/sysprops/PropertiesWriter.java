package org.glassfish.jersey.examples.sysprops;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.message.MessageUtils;

@Produces(MediaType.TEXT_PLAIN)
public class PropertiesWriter implements MessageBodyWriter<Set<String>> {
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Set.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Set<String> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		if(t == null || t.isEmpty())
			return;
		
		try(OutputStreamWriter isr = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType));) {
			for (String s : t) 
				isr.append(s).append('\n');
		}
		
	}

}
