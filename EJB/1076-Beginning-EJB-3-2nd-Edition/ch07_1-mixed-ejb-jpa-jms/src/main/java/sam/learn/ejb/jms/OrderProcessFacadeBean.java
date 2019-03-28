package sam.learn.ejb.jms;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import sam.learn.ejb.jpa.Customer;
import sam.learn.ejb.jpa.CustomerOrder;
import sam.learn.ejb.jpa.Distributor;
import sam.learn.ejb.jpa.Individual;
import sam.learn.ejb.jpa.Item;
import sam.learn.ejb.jpa.ItemsCart;
import sam.learn.ejb.jpa.ItemsOrder;
import sam.learn.ejb.jpa.MemberStatus;
import sam.learn.web.services.CreditCheckEndpointBean;
import sam.learn.web.services.CreditService;

@Stateless
public class OrderProcessFacadeBean {
	@PersistenceContext 
	private EntityManager em;

	@Inject 
	private JMSContext jms;

	@Resource(mappedName="JMSTopic")
	private Topic topic;

	@WebServiceRef(type=CreditService.class)
	private CreditService service;

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

	private boolean performCreditCheck(Individual individual){
		String ccname = Objects.requireNonNull(individual.getCcNum());
		CreditCheckEndpointBean e = service.getCreditCheckEndpointBeanPort();
		return e.creditCheck(ccname);
	}
	public String processOrder (Customer customer) {
		if(!em.contains(customer))
			customer = em.merge(customer);

		String processStatus = null;
		if(customer  instanceof Individual) {
			if(!performCreditCheck((Individual) customer))
				processStatus = "Invalid Credit Card number or credit check failed";
		} else if(customer instanceof Distributor) {
			if(MemberStatus.APPROVED != ((Distributor)customer).getMemberStatus())
				processStatus = "Distributor credit check rejected";
		}

		if(processStatus != null)
			return processStatus;

		CustomerOrder order = new CustomerOrder();
		order.setCreationDate(new Date());

		em.persist(order);
		List<ItemsCart> cart = customer.getItemsCart();

		if(cart != null && !cart.isEmpty()) {
			cart.forEach(ct -> {
				ItemsOrder o = new ItemsOrder();
				int qty = ct.getQuantity();
				o.setQuantity(qty);
				o.setOrderDate(new Date(System.currentTimeMillis()));
				o.setItem(ct.getItem());
				Item t = ct.getItem();
				float d = t.getRetailPrice();
				float price = d * ct.getQuantity();
				o.setPrice(price);
				order.addOrderItem(o);

				em.remove(ct);
			});
			customer.clearCustomerCart();
		}
		customer.addCustomerOrder(order);

		sendPOtoMDB(new PurchaseOrder(customer, order));

		return "Purchase Order sent for processing to the process queue";
	}

	private void sendPOtoMDB(PurchaseOrder order) {
		jms.createProducer().send(topic, jms.createObjectMessage(order));
	}
	
	@Remove
	public void remove() {
		jms.close();
	}
}
