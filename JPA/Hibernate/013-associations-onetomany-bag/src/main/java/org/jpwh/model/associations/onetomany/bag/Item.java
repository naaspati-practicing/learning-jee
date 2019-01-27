package org.jpwh.model.associations.onetomany.bag;

import java.util.ArrayList;
import java.util.Collection;

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
	private Collection<Bid> bids = new ArrayList<>();
	
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
	
	public Collection<Bid> getBids() {
		return bids;
	}
	public void setBids(Collection<Bid> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", bids=" + bids + "]";
	}
}
