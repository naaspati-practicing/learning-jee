package sam.learn.ejb.jms;

import java.io.Serializable;

import sam.learn.ejb.jpa.Customer;
import sam.learn.ejb.jpa.CustomerOrder;

public class PurchaseOrder implements Serializable {
	private static final long serialVersionUID = -2900599578386465292L;
	
	private Customer customer;
	private CustomerOrder customerOrder;
	
	public PurchaseOrder() { }
	
	public PurchaseOrder(Customer customer, CustomerOrder customerOrder) {
		this.customer = customer;
		this.customerOrder = customerOrder;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}
	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

}
