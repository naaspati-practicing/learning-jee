package org.glassfish.jersey.examples.xmlmoxy.beans;

import java.util.Objects;

public class Address {
	private String street;
	private String city;
	
	public Address() { }
	public Address(String street, String city) {
		this.street = street;
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public int hashCode() {
		return Objects.hash(city, street);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(street, other.street);
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + "]";
	}
}
