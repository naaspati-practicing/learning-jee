package org.jpwh.model.associations.ternary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;

import org.json.JSONObject;

@Entity
public class Category {
	
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@MapKeyJoinColumn(name="ITEM_ID") // Defaults to ITEMADDEDBY_KEY
	@JoinTable(
			name="CATEGORY_ITEM",
			joinColumns=@JoinColumn(name="CATEGORY_ID"),
			inverseJoinColumns=@JoinColumn(name="USER_ID")
			)
	private Map<Item, User> itemAddedBy = new HashMap<>();

	public String getName() {
		return name;
	}
	public Category() {}
	public Category(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Item, User> getItemAddedBy() {
		return itemAddedBy;
	}

	public void setItemAddedBy(Map<Item, User> itemAddedBy) {
		this.itemAddedBy = itemAddedBy;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("name", name);
		
		if(itemAddedBy != null) {
			List<JSONObject> list = new ArrayList<>();
					
			itemAddedBy.forEach((s,t) -> {
				JSONObject temp = new JSONObject();
				temp.put("key", s.toJsonObject());
				temp.put("value", t.toJsonObject());
				list.add(temp);
			});
			j.put("itemAddedBy", list);
		}
		
		return j.toString(4);
	}
}
