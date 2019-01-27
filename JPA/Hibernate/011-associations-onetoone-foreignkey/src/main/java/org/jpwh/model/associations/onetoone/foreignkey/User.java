package org.jpwh.model.associations.onetoone.foreignkey;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	@Id @GeneratedValue
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false, cascade=CascadeType.PERSIST)
	@JoinColumn(unique=true)
	private Address shippingAddress;
	
	private String username;

	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", shippingAddress=" + shippingAddress + ", username=" + username + "]";
	}
}
