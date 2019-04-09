package sam.learn.jaxb.relationship.selfmapping;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import sam.learn.jaxb.User;

/**
 * "Self" Mappings example
 *
 */

@XmlRootElement
class Employee extends User {
	private double salary;
	@XmlPath(".") // self mapping
	private Address address;

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
}
