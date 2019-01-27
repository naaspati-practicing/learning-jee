package org.jpwh.model.associations.maps;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;

@Entity
public class Bid {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	private Item item;
	
	@NotNull private BigDecimal amount;
	
	public Bid() {}
	
	public Bid(BigDecimal amount) {
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
	public JSONObject toJsonObject() {
		JSONObject o = new JSONObject();
		o.put("id", id);
		o.put("amount", amount);
		
		return o;
	}
}
