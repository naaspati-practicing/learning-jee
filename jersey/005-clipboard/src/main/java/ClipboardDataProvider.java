import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.MessageUtils;
import org.json.JSONObject;

public class ClipboardDataProvider {
	@Provider
	@Produces(APPLICATION_JSON)
	public static class ClipboardDataWriter<E extends ClipboardData> implements MessageBodyWriter<E> {

		@Override
		public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return check(type, mediaType);
		}

		@Override
		public void writeTo(E e, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
						throws IOException, WebApplicationException {

			try(OutputStreamWriter sw = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType))) {
				JSONObject obj = new JSONObject();
				obj.put("id", e.getId());
				obj.put("content", e.getContent());

				obj.write(sw);
			}
		}
	}
	
	@Provider
	@Consumes(APPLICATION_JSON)
	public static class ClipboardDataReader<E extends ClipboardData> implements MessageBodyReader<E> {
		@Override
		public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return check(type, mediaType);
		}
		@Override
		public E readFrom(Class<E> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
			String str = read(entityStream, MessageUtils.getCharset(mediaType));
			E e;
			try {
				e = type.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				throw new RuntimeException(e1);
			}
			
			JSONObject obj = new JSONObject(str);
			if(obj.has("id"))
				e.setId(obj.getInt("id"));
			if(obj.has("content"))
				e.setContent(obj.getString("content"));
			
			return e;
		}
	}

	private static String read(InputStream entityStream, Charset charset) throws IOException {
		StringBuilder sb = new StringBuilder();
		char[] chars = new char[200];
		
		try(InputStreamReader rdr = new InputStreamReader(entityStream, charset); ) {
			int n = 0;
			while((n = rdr.read(chars)) != -1)
				sb.append(chars, 0, n);
		}
		return sb.toString();
	}

	public static boolean check(Class<?> type, MediaType mediaType) {
		return type.isAssignableFrom(ClipboardData.class)  && mediaType.isCompatible(APPLICATION_JSON_TYPE);
	}


}
