package org.jpwh.model.simple;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Serializable {
	private static final long serialVersionUID = -4551016630492614611L;
	
	public User() {}
	
	public User(String username, Address homeAddress, Address billingAddress) {
		this.username = username;
		this.homeAddress = homeAddress;
		this.billingAddress = billingAddress;
	}

	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	private Address homeAddress;
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	@Embedded // not needed
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="BILLING_STREET")),
		@AttributeOverride(name="city", column=@Column(name="BILLING_CITY")),
		@AttributeOverride(name="zipcode", column=@Column(name="BILLING_ZIPCODE", length=5))
	})
	private Address billingAddress;
	
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public BigDecimal calcShippingCosts(Address fromLocation) {
        // Empty implementation of business method
        return null;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", homeAddress=" + homeAddress + ", billingAddress="
				+ billingAddress + "]";
	}
}
