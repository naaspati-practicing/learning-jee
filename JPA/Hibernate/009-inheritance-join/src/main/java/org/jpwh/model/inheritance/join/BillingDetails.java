package org.jpwh.model.inheritance.join;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class BillingDetails {
	@NotNull
	protected String owner;
	
	@Id @GeneratedValue
	private long id;
	
	public BillingDetails() {}
	public BillingDetails(String owner) {
		this.owner = owner;
	}
	
	public long getId() {
		return id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}
