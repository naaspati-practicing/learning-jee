package sam.grizzly.jersey;

import javax.json.Json;
import javax.json.JsonObject;

public class Obj {

	private String name;
	private long time;

	public Obj() {
	}
	public Obj(String name, long time) {
		this.name = name;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long id) {
		this.time = id;
	}

	public JsonObject toJsonObject() {
		return Json.createObjectBuilder()
				.add("name", name)
				.add("time", time)
				.build();

	}
}
