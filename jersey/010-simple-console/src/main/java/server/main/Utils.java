package server.main;

import org.glassfish.jersey.server.ResourceConfig;

public interface Utils {
	String SERVER_URI = "http://localhost:8080/";

	public static String lookup(String s, String defaultValue) {
		String value = System.getProperty(s);
		if(value != null)
			return value;
		
		value = System.getenv(s);
		if(value != null)
			return value;
		
		return value == null ? defaultValue : value;
	}
}
