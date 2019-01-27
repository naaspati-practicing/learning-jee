package org.jpwh.model.simple;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
	
	@NotNull // for validation -> ignored in DDL generation 
	@Column(nullable=false) // Used for DDL generation! -> adds constraint NOT NULL to column
	 private String street;
	
	@NotNull  
	@Column(nullable=false, length=5) // Overrides VARCHAR(255) i.e. column -> zipcode VARCHAR(5) NOT NULL 
	 private String zipcode;
	
	@NotNull 
	@Column(nullable=false)
	 private String city;
	
	public Address() {}

	public Address(String street, String zipcode, String city) {
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

	@Override
	public String toString() {
		return "Address [street=" + street + ", zipcode=" + zipcode + ", city=" + city + "]";
	}
}
