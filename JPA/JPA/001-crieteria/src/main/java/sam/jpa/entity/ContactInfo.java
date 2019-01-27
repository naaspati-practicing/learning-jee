package sam.jpa.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class ContactInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	@Version private long version;
	@OneToMany private Collection<Phone> phones;
	private Address address;
	
	public int getId() {
		return id;
	}
	public long getVersion() {
		return version;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}

