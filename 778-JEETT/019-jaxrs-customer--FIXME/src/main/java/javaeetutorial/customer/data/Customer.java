package javaeetutorial.customer.data;

import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="CUSTOMER_CUSTOMER")
@NamedQuery(name=Customer.FIND_ALL, query="SELECT c FROM Customer ORDER BY c.id")
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
	
	public static final String FIND_ALL = "javaeetutorial.customer.data.Customer.FIND_ALL";
	private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
	
	@XmlAttribute(required=true)
	@Id @GeneratedValue
	private long id;
	
	@XmlElement(required=true) protected String firstName;
	@XmlElement(required=true) protected String lastName;
	
	@XmlElement(required=true)
	@OneToOne
	protected Address address;
	
	@XmlElement(required=true) protected String email;
	@XmlElement(required=true) protected String phone;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		logCall(id);
		this.id = id;
	}
	private void logCall(Object obj) {
		LOGGER.info(() -> Thread.currentThread().getStackTrace()[2]+" ["+obj+"]");
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		logCall(firstName);
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		logCall(lastName);
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		logCall(address);
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		logCall(email);
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		logCall(phone);
		this.phone = phone;
	}
}
