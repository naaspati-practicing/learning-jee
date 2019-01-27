package org.jpwh.model.associations.onetomany.list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	@OneToMany
	@JoinColumn(
			name ="ITEM_ID",
			nullable=false
			)
	@OrderColumn(
			name="BID_POSITION", // Defaults to BIDS_ORDER
			nullable=false
			)
	private List<Bid> bids = new ArrayList<>();
	
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
	
	public List<Bid> getBids() {
		return bids;
	}
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", bids=" + bids + "]";
	}
}
