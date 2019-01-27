package org.jpwh.model.associations.onetomany.embeddable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USERS")
public class User {
	@Id @GeneratedValue
	private long id;
	
	@NotNull
	private String username;
	
	private Address shippingAddress;
	
	public User() {
    }

    public User(String username) {
        this.username = username;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", shippingAddress=" + shippingAddress + "]";
	}
	
	
}
