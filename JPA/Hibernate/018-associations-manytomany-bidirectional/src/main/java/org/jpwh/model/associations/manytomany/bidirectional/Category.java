package org.jpwh.model.associations.manytomany.bidirectional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
			joinColumns=@JoinColumn(name="CAT_ID"),
			inverseJoinColumns= @JoinColumn(name="ITEM_ID")
			)
	private Set<Item> items = new HashSet<>();
	
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", items=" + items + "]";
	}
	
	
}
