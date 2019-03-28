package sam.learn.ejb.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = -8127263401606682203L;

	@Id
	@Column(nullable=false)
	@GeneratedValue
	private int id;
	
	@Version
	private int version;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }
}
