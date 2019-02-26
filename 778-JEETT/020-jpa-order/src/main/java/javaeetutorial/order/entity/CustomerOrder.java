package javaeetutorial.order.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@NamedQuery(name=CustomerOrder.FIND_ALL, query="SELECT c FROM CustomerOrder c ORDER BY c.orderId")
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = 1682781378467830818L;
	public static final String FIND_ALL = "javaeetutorial.order.entity.CustomerOrder.FIND_ALL";
	
	@Id
	private int orderId;
	
	private char status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	
	private int discount;
	private String shippingInfo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customerOrder")
	private Collection<LineItem> lineItems;
	
	public CustomerOrder() { }

	public CustomerOrder(Integer orderId, char status, int discount, String shipmentInfo) {
		this.orderId = orderId;
		this.status = status;
		this.discount = discount;
		this.shippingInfo = shipmentInfo;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getShippingInfo() {
		return shippingInfo;
	}
	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}
	public Collection<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(Collection<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public void addLineItem(LineItem item) {
		getLineItems().add(item);
	}
	
	public double calculateAmmount() {
		Collection<LineItem> items = getLineItems();
		if(items.isEmpty())
			return 0;
		
		double amt = items.stream()
		.mapToDouble(v -> v.getQuantity() * v.getVendorPart().getPrice())
		.sum();
		
		return (amt * (100 - getDiscount()))/100;
	}
	
	@Transient
	public int nextId() {
		return getLineItems().size() + 1;
	}

}
