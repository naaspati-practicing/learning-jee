package org.jpwh.model.associations.maps;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;
	
	@NotNull private String name;
	
	
	@MapKey(name="id")
	@OneToMany(mappedBy="item", cascade=CascadeType.PERSIST)
	private Map<Long, Bid> bids = new HashMap<>();
	
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

	public Map<Long, Bid> getBids() {
		return bids;
	}

	public void setBids(Map<Long, Bid> bids) {
		this.bids = bids;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return toJsonObject().toString(4);
	}

	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("name", name);
		if(bids != null) {
			Map<Long, JSONObject> map = new HashMap<>();
			bids.forEach((s,t) -> map.put(s, t.toJsonObject()));
			object.put("bids", map);	
		}
		return object;
	}
	
}
