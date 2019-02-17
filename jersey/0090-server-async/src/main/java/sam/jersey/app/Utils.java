package sam.jersey.app;

import org.json.JSONObject;

public class Utils {
	
	public static JSONObject asJson(Object...key_value_pairs) {
		if(key_value_pairs == null || key_value_pairs.length == 0)
			return new JSONObject();
		
		JSONObject json = new JSONObject();
		
		int n = 0;
		while(n < key_value_pairs.length) 
			json.put((String)key_value_pairs[n++], key_value_pairs[n++]);
		
		return json;
	}

}
