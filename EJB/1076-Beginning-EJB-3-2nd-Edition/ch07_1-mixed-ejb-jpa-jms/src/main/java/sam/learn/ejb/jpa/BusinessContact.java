package sam.learn.ejb.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CH07_BUSINESS_CONTACT")
@NamedQuery(name=BusinessContact.NAMED_QUERY_FIND_ALL, query="select o from BusinessContact o")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class BusinessContact implements Serializable {
	private static final long serialVersionUID = -6794516195421569109L;
	
	public static final String NAMED_QUERY_FIND_ALL = "BusinessContact.findAll";

	@Id
	@Column(nullable=false)
	@GeneratedValue
	private int id;
	
	@Version
	private int version;
	
	
	@Column(name="FIRST_NAME", length = 4000) private String firstName;
	@Column(name="LAST_NAME" , length = 4000) private String lastName;

	@Column(length=15) private String phone;

	public BusinessContact() { }

	public BusinessContact(String firstName, String lastName, String phone) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
	}

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }
}
