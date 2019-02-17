package org.glassfish.jersey.examples.entityfiltering.selectable.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address {
	private String street;
	private String region;
	private PhoneNumber number;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public PhoneNumber getPhoneNumber() {
		return number;
	}
	public void setPhoneNumber(PhoneNumber number) {
		this.number = number;
	}
}
