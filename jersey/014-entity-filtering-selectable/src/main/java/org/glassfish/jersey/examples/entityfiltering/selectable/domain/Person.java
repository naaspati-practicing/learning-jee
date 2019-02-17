package org.glassfish.jersey.examples.entityfiltering.selectable.domain;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Person {
	private String givenName;
	private String familyName;
	private String honorificSuffix;
	private String honorificPrefix;
	
	// same name as Address.region
	private String region;
	
	private List<Address> addresses;
	private Map<String, PhoneNumber> phoneNumbers;
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getHonorificSuffix() {
		return honorificSuffix;
	}
	public void setHonorificSuffix(String honorificSuffix) {
		this.honorificSuffix = honorificSuffix;
	}
	public String getHonorificPrefix() {
		return honorificPrefix;
	}
	public void setHonorificPrefix(String honorificPrefix) {
		this.honorificPrefix = honorificPrefix;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public Map<String, PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(Map<String, PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
