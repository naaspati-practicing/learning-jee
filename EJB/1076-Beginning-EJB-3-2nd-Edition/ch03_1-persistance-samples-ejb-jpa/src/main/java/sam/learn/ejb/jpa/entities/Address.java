package sam.learn.ejb.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CH03_ADDRESS")
@NamedQuery(name=Address.NAMED_QUERY_FIND_ALL, query="select o from Address o")
public class Address implements Serializable {
	private static final long serialVersionUID = -281200260428405703L;

	public static final String NAMED_QUERY_FIND_ALL = "Address.findAll";
	
	@Id
	@Column(nullable=false)
	@GeneratedValue
	private int id;
	
	@Version
	private int version;
	
	@Column(length=4000) private String city;
	@Column(length=4000) private String state;
	@Column(length=4000) private String street1;
	@Column(length=4000) private String street2;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	public Address() { }

	public Address(String city, String state, String street1, String street2, String zipCode) {
		super();
		this.city = city;
		this.state = state;
		this.street1 = street1;
		this.street2 = street2;
		this.zipCode = zipCode;
	}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	public String getStreet1() { return street1; }
	public void setStreet1(String street1) { this.street1 = street1; }

	public String getStreet2() { return street2; }
	public void setStreet2(String street2) { this.street2 = street2; }

	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }
	
}
