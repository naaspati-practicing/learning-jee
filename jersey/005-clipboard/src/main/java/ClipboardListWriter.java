import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.MessageUtils;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings({"unchecked", "rawtypes"})
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ClipboardListWriter implements MessageBodyWriter {

	@Override
	public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE) && 
				Collection.class.isAssignableFrom(type) &&
				((ParameterizedType)genericType).getActualTypeArguments()[0] == ClipboardData.class;
	}
	@Override
	public void writeTo(Object t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		Collection<ClipboardData> list = (Collection<ClipboardData>) t;
		if(list.isEmpty()) {
			entityStream.write("[]".getBytes(MessageUtils.getCharset(mediaType)));
			return;
		}
		JSONArray array = new JSONArray();

		for (ClipboardData e : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", e.getId());
			obj.put("content", e.getContent());

			array.put(obj);
		}
		try(OutputStreamWriter sw = new OutputStreamWriter(entityStream, MessageUtils.getCharset(mediaType))) {
			array.write(sw);	
		}

	}

}
