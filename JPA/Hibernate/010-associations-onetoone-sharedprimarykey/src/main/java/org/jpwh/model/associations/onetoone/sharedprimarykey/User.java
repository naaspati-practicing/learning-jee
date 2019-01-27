package org.jpwh.model.associations.onetoone.sharedprimarykey;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	@Id // id is obtained from shippingAddress
	private long id;
	
	@OneToOne(fetch=FetchType.LAZY, optional=false)
	@PrimaryKeyJoinColumn
	private Address shippingAddress;
	
	private String username;

	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
		this.id = shippingAddress.getId();
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
