package sam.learn.ejb.jpa;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import sam.learn.ejb.jpa.entities.Address;
import sam.learn.ejb.jpa.entities.Customer;
import sam.learn.ejb.jpa.entities.CustomerOrder;
import sam.learn.ejb.jpa.entities.OrderStatus;

@Stateless
public class CustomerOrderManager {
	
	@PersistenceContext
	private EntityManager em;
	
	public <T> T persist(T entity) {
		em.persist(entity);
		return entity;
	}
	public <T> T merge(T entity) {
		em.merge(entity);
		return entity;
	}
	public void remove(Customer c) {
		Objects.requireNonNull(c);
		em.remove(em.find(Customer.class, c.getId()));
	}
	public void remove(Address c) {
		Objects.requireNonNull(c);
		em.remove(em.find(Address.class, c.getId()));
	}
	public void remove(CustomerOrder c) {
		Objects.requireNonNull(c);
		em.remove(em.find(CustomerOrder.class, c.getId()));
	}
	
	public List<Customer> findAllCustomers() {
		return em.createNamedQuery(Customer.NAMED_QUERY_FIND_ALL, Customer.class).getResultList();
	}
	public List<Address> findAllAddresses() {
		return em.createNamedQuery(Address.NAMED_QUERY_FIND_ALL, Address.class).getResultList();
	}
	public List<CustomerOrder> findAllCustomerOrders() {
		return em.createNamedQuery(CustomerOrder.NAMED_QUERY_FIND_ALL, CustomerOrder.class).getResultList();
	}
	
	public List<CustomerOrder> findCustomerOrderByEmail(String email) {
		return em.createNamedQuery(CustomerOrder.NAMED_QUERY_FIND_BY_EMAIL, CustomerOrder.class)
				.setParameter("email", email)
				.getResultList();
	}
	public int bulkDeleteFulfilledOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaDelete<CustomerOrder> delete = cb.createCriteriaDelete(CustomerOrder.class);
		Root<CustomerOrder> root = delete.from(CustomerOrder.class);
		delete.where(cb.equal(root.get("status"), OrderStatus.FULFILLED));
		
		return em.createQuery(delete).executeUpdate();
	}
}
