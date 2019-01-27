package org.jpwh.model.associations.onetoone.foreignkey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@Id @GeneratedValue
	private long id;
	
	@NotNull private String street;
	@NotNull private String zipcode;
	@NotNull private String city;
	
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
	public long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", zipcode=" + zipcode + ", city=" + city + "]";
	}
}
