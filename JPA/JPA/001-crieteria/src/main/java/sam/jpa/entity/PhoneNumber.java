package sam.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class PhoneNumber implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	
	public void addCustomer(PhoneNumber phone) {
		// TODO Auto-generated method stub
		
	}
	
	
}

