package com.actionbazaar.persistence;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	public Address() { }
	
	public Address(String street1, String street2, String city, String state, String zipCode, String country) {
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}
	
	public String getStreet1(){ return this.street1; }
	public void setStreet1(String street1){ this.street1 = street1; }

	public String getStreet2(){ return this.street2; }
	public void setStreet2(String street2){ this.street2 = street2; }

	public String getCity(){ return this.city; }
	public void setCity(String city){ this.city = city; }

	public String getState(){ return this.state; }
	public void setState(String state){ this.state = state; }

	public String getZipCode(){ return this.zipCode; }
	public void setZipCode(String zipCode){ this.zipCode = zipCode; }

	public String getCountry(){ return this.country; }
	public void setCountry(String country){ this.country = country; }
}
