package sam.learn.ejb.jpa;

import static javax.persistence.CascadeType.ALL;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CH07_CUSTOMER")
@NamedQueries({
	@NamedQuery(name=Customer.NAMED_QUERY_FIND_ALL, query="select o from Customer o"),
	@NamedQuery(name=Customer.NAMED_QUERY_FIND_BY_EMAIL, query="select o from Customer o where o.email = :email"),
})
public class Customer extends BusinessContact {
	private static final long serialVersionUID = -1605458125735708514L;

	public static final String NAMED_QUERY_FIND_ALL = "Customer.findAll";
	public static final String NAMED_QUERY_FIND_BY_EMAIL = "Customer.findByEmail";
	
	@Column(length = 4000) private String email;

	@OneToMany(mappedBy="customer", cascade=ALL, orphanRemoval=true)
	private List<ItemsCart> ItemsCart;

	@OneToMany(mappedBy="customer", cascade=ALL, orphanRemoval=true)
	private List<CustomerOrder> customerOrders;

	@JoinTable(
			name = "CUSTOMER_BILLING_ADDRESS",
			joinColumns=@JoinColumn(name="CUSTOMER_ID"),
			inverseJoinColumns=@JoinColumn(name="ADDRESS_ID")
			)
	@OneToMany(cascade=ALL, orphanRemoval=true)
	private List<Address> billingAddresses;

	@JoinTable(
			name = "CUSTOMER_SHIPPING_ADDRESS",
			joinColumns=@JoinColumn(name="CUSTOMER_ID"),
			inverseJoinColumns=@JoinColumn(name="ADDRESS_ID")
			)
	@OneToMany(cascade=ALL, orphanRemoval=true)
	private List<Address> shippingAddresses;


	@OneToOne(cascade = ALL)
	@JoinColumn(name = "DEFAULT_SHIPPING_ADDRESS")
	private Address defaultShippingAddress;

	@OneToOne(cascade = ALL)
	@JoinColumn(name = "DEFAULT_BILLING_ADDRESS")
	private Address defaultBillingAddress;
	
	public Customer() { }

	  public Customer(String firstName, String lastName, String phone, String email, Address defaultShippingAddress,
	      Address defaultBillingAddress) {
	    super(firstName, lastName, phone);
	    setEmail(email);
	    setDefaultBillingAddress(defaultBillingAddress);
	    setDefaultShippingAddress(defaultShippingAddress);
	  }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public List<ItemsCart> getItemsCart() { return ItemsCart; }
	public void setItemsCart(List<ItemsCart> ItemsCart) { this.ItemsCart = ItemsCart; }
	public List<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}
	public void setCustomerOrders(List<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}
	public List<Address> getBillingAddresses() {
		return billingAddresses;
	}
	public void setBillingAddresses(List<Address> billingAddresses) {
		this.billingAddresses = billingAddresses;
	}
	public List<Address> getShippingAddresses() {
		return shippingAddresses;
	}
	public void setShippingAddresses(List<Address> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}
	public Address getDefaultShippingAddress() {
		return defaultShippingAddress;
	}
	public void setDefaultShippingAddress(Address defaultShippingAddress) {
		this.defaultShippingAddress = defaultShippingAddress;
	}
	public Address getDefaultBillingAddress() {
		return defaultBillingAddress;
	}
	public void setDefaultBillingAddress(Address defaultBillingAddress) {
		this.defaultBillingAddress = defaultBillingAddress;
	}
	
	public void addCartItem(ItemsCart item) {
		Objects.requireNonNull(item);
		getItemsCart().add(item);
		item.setCustomer(this);
	}
	public void removeCartItem(ItemsCart item) {
		if(item == null)
			return;
	
		if(getItemsCart().remove(item))
			item.setCustomer(null);
	}
	public void clearCustomerCart() {
		List<ItemsCart> cart = getItemsCart();
		
		if(cart.isEmpty())
			return;
		
		cart.forEach(c -> c.setCustomer(null));
		cart.clear();
	}

	public void addCustomerOrder(CustomerOrder item) {
		Objects.requireNonNull(item);
		getCustomerOrders().add(item);
		item.setCustomer(this);
	}
	public void removeCustomerOrder(CustomerOrder item) {
		if(item == null)
			return;
	
		if(getCustomerOrders().remove(item))
			item.setCustomer(null);
	}
}
