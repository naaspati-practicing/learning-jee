package org.jpwh.model.associations.manytomany.embeddable.component;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.json.JSONObject;

@Entity
public class Category {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@ElementCollection
	@CollectionTable(
			name="CATEGORY_ITEM",
			joinColumns=@JoinColumn(name="CATEGORY_ID")
			)
	private Set<CategorizedItem> categorizedItems = new HashSet<>();
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CategorizedItem> getCategorizedItems() {
		return categorizedItems;
	}

	public void setCategorizedItems(Set<CategorizedItem> categorizedItems) {
		this.categorizedItems = categorizedItems;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("name", name);
		object.put("categorizedItems", categorizedItems);
		
		return object.toString(4);
	}
}
