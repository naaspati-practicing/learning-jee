package server.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.message.internal.ReaderWriter;

import j2html.tags.Renderable;

@Produces(MediaType.TEXT_HTML)
@Provider
public class RenderableWriter implements MessageBodyWriter<Renderable> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Renderable.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Renderable t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		OutputStreamWriter os = new OutputStreamWriter(entityStream, ReaderWriter.getCharset(mediaType));
		t.render(os);
		os.flush();
	}
}
