package org.jpwh.model.associations.onetomany.jointable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id @GeneratedValue
	private long id;

	private String name;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(
			name = "ITEM_BUYER",
			joinColumns = @JoinColumn(name="ITEM_ID"), // // Defaults to ID
			inverseJoinColumns= @JoinColumn(nullable=false) // Defaults to BUYER_ID
			)
	private User buyer;

	public Item() {}

	public Item(String name) {
		this.name = name;
	}
	public Item(String name, User buyer) {
		setName(name);
		setBuyer(buyer);
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

	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
}
