package sam.learn.jaxb.relationship;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import sam.learn.jaxb.User;

@XmlRootElement
class Employee extends User {
	private double salary;
	private Address address;
	
	// @OneToMany(mappedBy="owner")
	private List<PhoneNumber> contactNumber;

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		if(this.address != null)
			this.address.setOwner(null);

		this.address = address;

		if(this.address != null)
			this.address.setOwner(this);
	}
	
	public void addContactNumber(String number) {
		PhoneNumber p = new PhoneNumber();
		p.setPhoneNumber(number);
		p.setOwner(this);
		
		if(contactNumber == null)
			contactNumber = new ArrayList<>();
		
		contactNumber.add(p);
	}
	public List<PhoneNumber> getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(List<PhoneNumber> contactNumber) {
		this.contactNumber = contactNumber;
	}
}
