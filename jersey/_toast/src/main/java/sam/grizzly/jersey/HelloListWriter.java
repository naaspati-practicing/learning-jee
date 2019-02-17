package sam.grizzly.jersey;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.MessageUtils;

@Provider
public class HelloListWriter implements MessageBodyWriter<List<HelloMessage>>{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if(!(Collection.class.isAssignableFrom(type) && (genericType instanceof ParameterizedType)))
			return false;
		
		Type[] pt = ((ParameterizedType) genericType).getActualTypeArguments();
		return pt.length == 1 && HelloMessage.class.isAssignableFrom((Class)pt[0]);
	}

	@Override
	public void writeTo(List<HelloMessage> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		
		try(OutputStreamWriter os = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType))) {
			for (HelloMessage hello : t) 
				os.append("HelloListWriter: ").append(hello.toString()).append("\n");
		}
	}
}
