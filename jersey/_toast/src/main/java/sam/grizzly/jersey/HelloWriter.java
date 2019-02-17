package sam.grizzly.jersey;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.MessageUtils;


@Provider
public class HelloWriter implements MessageBodyWriter<HelloMessage> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return HelloMessage.class.isAssignableFrom(type);
	}
	@Override
	public void writeTo(HelloMessage t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {
		try(OutputStreamWriter os = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType))) {
			os.write("HelloWriter: "+t);
		}
	}

}
