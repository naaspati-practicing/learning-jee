package sam.learn.ejb.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CH07_ADDRESS")
@NamedQuery(name=Address.NAMED_QUERY_FIND_ALL, query="select o from Address o")
public class Address extends BaseEntity {
	private static final long serialVersionUID = -281200260428405703L;

	public static final String NAMED_QUERY_FIND_ALL = "Address.findAll";

	@Column(length = 4000) private String city;
	@Column(length = 4000) private String state;
	@Column(length = 4000) private String street1;
	@Column(length = 4000) private String street2;

	@Column(name="ZIP_CODE")
	private String zipCode;

	public Address() {
	}

	public Address(String city, String state, String street1, String street2, String zipCode) {
		setCity(city);
		setState(state);
		setStreet1(street1);
		setStreet2(street2);
		setZipCode(zipCode);
	}

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
