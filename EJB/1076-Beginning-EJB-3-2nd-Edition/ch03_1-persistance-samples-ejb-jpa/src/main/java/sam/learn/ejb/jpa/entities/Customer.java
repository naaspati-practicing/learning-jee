package sam.learn.ejb.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="CH03_CUSTOMER")
@NamedQueries({
	@NamedQuery(name=Customer.NAMED_QUERY_FIND_ALL, query="select o from Customer o"),
	@NamedQuery(name=Customer.NAMED_QUERY_FIND_BY_EMAIL, query="select o from Customer o where o.email = :email"),
})
public class Customer implements Serializable {
	private static final long serialVersionUID = -6167770202044500242L;
	
	public static final String NAMED_QUERY_FIND_BY_EMAIL = "Customer.findByEmail";
	public static final String NAMED_QUERY_FIND_ALL = "Customer.findAll";
	
	@Id
	@Column(nullable=false)
	@GeneratedValue
	private int id;

	@Version 
	private int version;

	@Column(length=4000)
	private String email;

	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<CustomerOrder> customerOrders;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="billing_address")
	private Address billingAddress;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="shipping_address")
	private Address shippingAddress;

	public Customer(String email, Address billingAddress, Address shippingAddress) {
		this.email = email;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
	}
	
	public Customer() { }
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public List<CustomerOrder> getCustomerOrders() { return customerOrders; }
	public void setCustomerOrders(List<CustomerOrder> customerOrders) { this.customerOrders = customerOrders; }

	public void add(CustomerOrder order) {
		getCustomerOrders().add(order);
		order.setCustomer(this);
	}
	public void remove(CustomerOrder order) {
		if(getCustomerOrders().remove(order))
			order.setCustomer(null);
	}

	public Address getBillingAddress() { return billingAddress; }
	public void setBillingAddress(Address billingAddress) { this.billingAddress = billingAddress; }

	public Address getShippingAddress() { return shippingAddress; }
	public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }



}
