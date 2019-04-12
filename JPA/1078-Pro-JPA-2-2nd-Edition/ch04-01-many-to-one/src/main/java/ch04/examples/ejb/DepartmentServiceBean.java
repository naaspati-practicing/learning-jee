package ch04.examples.ejb;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch04.examples.model.Department;
import ch04.examples.model.Employee;

@Stateless
public class DepartmentServiceBean implements DepartmentService {
	
	@PersistenceContext(name="postgresDS")
	private EntityManager em;
	
	public DepartmentServiceBean() { }
	
	public DepartmentServiceBean(EntityManager em) { 
		this.em = em;
	}

	@Override
	public Department addDepartment(String name) {
		Department d = new Department(name);
		em.persist(d);
		return d;
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> list = em.createNamedQuery(Department.NAMED_QUERY_FIND_ALL, Department.class).getResultList();
		
		if(list.isEmpty())
			return Collections.singletonList(addDepartment("default"));
		else
			return list;
	}

	// @Override
	public Employee  addEmployee(Department department, Employee e) {
		department = em.merge(department);
		e.setDepartment(department);
		em.persist(e);
		return e;
	}
	
	@Override
	public Employee  addEmployee(int department_id, Employee e) {
		return addEmployee(em.find(Department.class, department_id), e);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return em.createNamedQuery(Employee.NAMED_QUERY_FIND_ALL, Employee.class).getResultList();
	}
	
	@Override
	public Employee getEmployee(int employee_id) {
		return em.find(Employee.class, employee_id);
	}
}
