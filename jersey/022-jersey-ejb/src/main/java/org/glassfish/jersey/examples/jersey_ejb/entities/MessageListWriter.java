package org.glassfish.jersey.examples.jersey_ejb.entities;

import static j2html.TagCreator.attrs;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.MessageUtils;

@Stateless
@Provider
public class MessageListWriter implements MessageBodyWriter<Collection<Message>> {

	@Context
	private javax.inject.Provider<UriInfo> ui;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if(!(Collection.class.isAssignableFrom(type) && (genericType instanceof ParameterizedType)))
			return false;
		
		Type[] pt = ((ParameterizedType) genericType).getActualTypeArguments();
		return pt.length == 1 && Message.class.isAssignableFrom((Class)pt[0]);
	}

	@Override
	public long getSize(Collection<Message> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Collection<Message> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {

		try(OutputStreamWriter os = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType))) {
			if(t == null || t.isEmpty()) {
				div(attrs(".messages")).render(os);
			} else {
				div(attrs(".messages"), 
						each(t, m -> m.toHtml(ui.get().getAbsolutePathBuilder().path(Integer.toString(m.getUniqueId())).build().toASCIIString()))
						) 
				.render(os);	
			}
		}
	}

}
