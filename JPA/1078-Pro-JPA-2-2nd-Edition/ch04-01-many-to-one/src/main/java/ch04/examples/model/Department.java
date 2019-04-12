package ch04.examples.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name="DEPARTMENTS")
@NamedQueries({
	@NamedQuery(name=Department.NAMED_QUERY_FIND_ALL, query="SELECT e FROM Department e"),
	@NamedQuery(name=Department.NAMED_QUERY_FIND_BY_NAME, query="SELECT e FROM Department e WHERE e.name = ?1"),
})
public class Department implements Serializable {
	private static final long serialVersionUID = -953770241580209067L;

	public static final String NAMED_QUERY_FIND_ALL = "Department.findAll";
	public static final String NAMED_QUERY_FIND_BY_NAME = "Department.findByName";
	
	@Id @GeneratedValue
	private int id;
	
	@Column(nullable=false, unique=true, length=10, updatable=false)
	@Index
	private String name;
	
	public Department(String name) {
		this.name = name;
	}
	
	public Department() { }
	
	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }

	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=\"" + name + "\"]";
	}
}
