import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("args", args);
		
		System.getProperties().forEach((s,t) -> json.put(s.toString(),t));
		System.getenv().forEach((s,t) -> json.put(s.toString(),t));
		
		System.err.println(json);
	}

}
