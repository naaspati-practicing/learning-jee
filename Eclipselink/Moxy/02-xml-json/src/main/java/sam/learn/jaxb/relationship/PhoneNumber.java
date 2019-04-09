package sam.learn.jaxb.relationship;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@XmlRootElement
class PhoneNumber {
	private String phoneNumber;
	
	// @ManyToOne
    // @JoinColumn(name="E_ID", referencedColumnName = "E_ID")
	@XmlInverseReference(mappedBy="contactNumber")
	private Employee owner;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Employee getOwner() {
		return owner;
	}
	public void setOwner(Employee owner) {
		this.owner = owner;
	}
}
