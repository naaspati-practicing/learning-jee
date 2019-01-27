package org.jpwh.model.associations.ternary;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.json.JSONObject;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	public Item() {}

	public Item(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}

	public JSONObject toJsonObject() {
		JSONObject o = new JSONObject();
		o.put("id", id);
		o.put("name", name);
		return o;
	}
}
