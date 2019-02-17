package org.glassfish.jersey.examples.httptrace;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;

import org.json.JSONException;
import org.json.JSONObject;

public interface Utils {
	String ROOT_PATH_PROGRAMMATIC = "tracing/programmatic";
	String ROOT_PATH_ANNOTATED = "tracing/annotated";

	static final Set<String> EXCLUDE_JSON =Collections.synchronizedSet(Collections.unmodifiableSet( new HashSet<>(Arrays.asList(
			"ResponseWriter",
			"SecurityContext",
			"VaryValue",
			"Workers",
			"RequestScopedInitializer",
			"Request",
			"QualifiedAcceptEncoding",
			"QualifiedAcceptCharset",
			"PropertyNames",
			"PropertiesDelegate",
			"EntityStream",
			"Class",
			"UriInfo"))));

	static String toJsonString(ContainerRequestContext req) throws JSONException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JSONObject json = new JSONObject();

		for (Method m : req.getClass().getMethods()) {
			if(m.getName().startsWith("get") && Modifier.isPublic(m.getModifiers()) && m.getParameterCount() == 0 && m.getReturnType() != Void.class ) {
				String name = m.getName().substring(3);
				if(!EXCLUDE_JSON.contains(name))
					json.put(name, String.valueOf(m.invoke(req)));
			}
		}

		json.put("generated_at", Thread.currentThread().getStackTrace()[2].toString());

		return json.toString(4);
	}
}
