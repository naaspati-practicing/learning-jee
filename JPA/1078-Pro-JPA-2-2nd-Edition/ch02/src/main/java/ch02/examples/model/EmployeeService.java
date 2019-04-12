package ch02.examples.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class EmployeeService {
	@PersistenceContext
	private EntityManager em;
	
	public EmployeeService() { }
	
	public EmployeeService(EntityManager em) {
		this.em = em;
	}
	
	public void persist(Employee e) {
		em.persist(e);
	}
	public void remove(Employee e) {
		em.remove(e);
	}
	public Employee find(int id) {
		return em.find(Employee.class, Integer.valueOf(id));
	}
	public List<Employee> findAll() {
		return em.createNamedQuery(Employee.NAMED_QUERY_FIND_ALL, Employee.class).getResultList();
	}
}
