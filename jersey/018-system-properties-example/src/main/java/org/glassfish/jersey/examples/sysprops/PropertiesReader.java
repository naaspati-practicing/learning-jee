package org.glassfish.jersey.examples.sysprops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.glassfish.jersey.message.MessageUtils;

@Consumes(MediaType.TEXT_PLAIN)
public class PropertiesReader implements MessageBodyReader<Set<String>> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Set.class.isAssignableFrom(type);
	}

	@Override
	public Set<String> readFrom(Class<Set<String>> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		try(InputStreamReader isr = new InputStreamReader(entityStream, MessageUtils.getCharset(mediaType));
				BufferedReader reader = new BufferedReader(isr);) {
			return reader.lines().collect(Collectors.toSet());
		}
	}

}
