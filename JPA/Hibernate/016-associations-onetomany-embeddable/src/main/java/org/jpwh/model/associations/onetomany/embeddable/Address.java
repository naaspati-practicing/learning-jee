package org.jpwh.model.associations.onetomany.embeddable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
	@NotNull
	@Column(nullable=false)
	private String street;
	
	@NotNull
	@Column(nullable=false, length=5)
	private String zipcode;
	
	@NotNull
	@Column(nullable=false)
	private String city;
	
	@OneToMany
	@JoinColumn(
			name = "DELIVERIES_ADDRESS_USER_ID", // Defaults to DELIVERIES_ID
			nullable=false
			)
	private Set<Shipment> deliveries = new HashSet<>();
	
	
	public Address() {}

	public Address(String street, String zipcode, String city) {
		super();
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Shipment> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Set<Shipment> deliveries) {
		this.deliveries = deliveries;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", zipcode=" + zipcode + ", city=" + city + ", deliveries=" + deliveries
				+ "]";
	}
}
