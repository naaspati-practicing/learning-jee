package ch02.examples.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEES")
@NamedQuery(name=Employee.NAMED_QUERY_FIND_ALL, query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 8637413868228566920L;
	public static final String NAMED_QUERY_FIND_ALL = "Employee.findAll"; 
	
	@Id @GeneratedValue
	private int id;
	private String name;
	private double salary;
	
	public Employee() { }
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	
	public int getId(){ return this.id; }
	void setId(int id){ this.id = id; }

	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }

	public double getSalary(){ return this.salary; }
	public void setSalary(double salary){ this.salary = salary; }

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=\"" + name + "\", salary=" + salary + "]";
	}
}
