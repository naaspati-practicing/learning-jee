package sam.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	@Version private long version;
	private String name;
	
	public int getId() {
		return id;
	}
	public long getVersion() {
		return version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

