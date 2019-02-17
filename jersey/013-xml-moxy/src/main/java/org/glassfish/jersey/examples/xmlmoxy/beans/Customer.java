package org.glassfish.jersey.examples.xmlmoxy.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement 
@XmlType(propOrder={"name", "address", "phoneNumbers"})
public class Customer {

	@XmlPath("personal-info/name/text()")
	private String name;
	@XmlPath("contact-info/address")
	private Address address;
	@XmlPath("contact-info/phone-number")
	private List<PhoneNumber> phoneNumbers;
	
	public Customer() {
		phoneNumbers = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && 
				Objects.equals(name, other.name) && 
				Objects.equals(phoneNumbers, other.phoneNumbers);
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phoneNumbers=" + phoneNumbers + "]";
	}
}
