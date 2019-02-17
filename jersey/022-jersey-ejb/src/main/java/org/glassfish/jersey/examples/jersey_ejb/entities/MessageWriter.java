package org.glassfish.jersey.examples.jersey_ejb.entities;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.MessageUtils;

@Stateless
@Provider
public class MessageWriter implements MessageBodyWriter<Message> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Message.class.isAssignableFrom(type);
	}

	@Override
	public long getSize(Message t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Message t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		try(OutputStreamWriter os = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType))) {
			t.toHtml(null).render(os);
		} 
	}

}
