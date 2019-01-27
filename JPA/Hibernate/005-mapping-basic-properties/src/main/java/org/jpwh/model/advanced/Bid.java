package org.jpwh.model.advanced;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Bid {
	public Bid() {}
	
	public Bid(Item item, BigDecimal bidAmount) {
		this.item = item;
		this.amount = bidAmount;
	}

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	private BigDecimal amount;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY) // NOT NULL
	@JoinColumn(name="ITEM_ID") // Actually the default name
	private Item item;
	
	public long getId() {
		return id;
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

	@Override
	public String toString() {
		return "Bid [id=" + id + ", amount=" + amount + ", item=" + item + "]";
	}
}
