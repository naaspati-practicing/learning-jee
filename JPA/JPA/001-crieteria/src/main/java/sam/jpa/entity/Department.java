package sam.jpa.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	@Version private long version;
	private String name;
	private int deptno;
	
	@OneToMany(mappedBy=Employee_.DEPARTMENT)
	private Collection<Employee>  employees = new HashSet<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}
	public int getId() {
		return id;
	}
	public long getVersion() {
		return version;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}

