package sam.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardTransaction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	
	public int getId() {
		return id;
	}
	
	
	
	
	
}

