package sam.learn.ejb;

import java.util.List;
import java.util.function.Function;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sam.learn.ejb.jpa.Customer;

@Stateless
public class CustomerFacadeBean {
	@PersistenceContext
	private EntityManager em;
	
	public <T> void persist(List<T> list) {
		list.forEach(em::persist);
	}
	
	public <T> T persist(T t) {
		em.persist(t);
		return t;
	} 
	public <T> T merge(T t) {
		return em.merge(t);
	}
	
	public List<Customer> getAllCustomer() {
		return em.createNamedQuery(Customer.NAMED_QUERY_FIND_ALL, Customer.class).getResultList();
	}
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}
	public Customer getCustomerByEmail(String email_address) {
		return em.createNamedQuery(Customer.NAMED_QUERY_FIND_BY_EMAIL, Customer.class)
				.setParameter("email", email_address)
				.getSingleResult();
	}
	

}
