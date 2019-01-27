package sam.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private int id;
	
	@ManyToOne private Customer customer;
	@OneToMany private Set<Item> lineItems;
	
	private Address shippingAddress;
	private BigDecimal totalCost;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Set<Item> getLineItems() {
		return lineItems;
	}
	public void setLineItems(Set<Item> lineItem) {
		this.lineItems = lineItem;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public int getId() {
		return id;
	}
}

