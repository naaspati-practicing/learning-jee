package sam.learn.jaxb.relationship.selfmapping;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@XmlRootElement
class Address {
	private String houseNumber;
	private String state;
	private String city;
	private String zipcode;
	
	@XmlInverseReference(mappedBy="address")	
	private Employee owner;
	
	public String getHouseNumber(){ return this.houseNumber; }
	public void setHouseNumber(String houseNumber){ this.houseNumber = houseNumber; }

	public String getState(){ return this.state; }
	public void setState(String state){ this.state = state; }

	public String getCity(){ return this.city; }
	public void setCity(String city){ this.city = city; }

	public String getZipcode(){ return this.zipcode; }
	public void setZipcode(String zipcode){ this.zipcode = zipcode; }

	public Employee getOwner(){ return this.owner; }
	public void setOwner(Employee owner){ this.owner = owner; }


	

}
