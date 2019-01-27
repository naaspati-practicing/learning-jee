package org.jpwh.model.associations.manytomany.embeddable.component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	public Item() {}
	public Item(String name) {this.name = name;}
	
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
}
