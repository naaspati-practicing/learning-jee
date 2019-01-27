package org.jpwh.model.associations.onetomany.bag;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Bid {
	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Item item;
	
	@NotNull
	private BigDecimal amount;
	
	public Bid() {}

	public Bid(BigDecimal amount, Item item) {
		this.item = item;
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id  + ", amount=" + amount + "]";
	}
}
