package org.jpwh.model.associations.manytomany.linkentity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy="item")
	private Set<CategorizedItem> categorizedItems = new HashSet<>();
	
	public Item() {}
	public Item(String name) {this.name = name;}

	public Set<CategorizedItem> getCategorizedItems() {
		return categorizedItems;
	}
	public void setCategorizedItems(Set<CategorizedItem> categorizedItems) {
		this.categorizedItems = categorizedItems;
	}
	public void setId(long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
}
