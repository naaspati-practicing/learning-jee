package javaeetutorial.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@IdClass(LineItemKey.class)
@Entity
@NamedQueries( {
	@NamedQuery(name=LineItem.FIND_ALL, query="SELECT e FROM LineItem e"),
	@NamedQuery(name=LineItem.FIND_BY_ORDER_ID, query="SELECT e FROM LineItem e WHERE e.customerOrder.orderId = :orderId ORDER BY e.itemId"),
	@NamedQuery(name=LineItem.FIND_BY_ORDER_N_ITEM_ID, query="SELECT DISTINCT e FROM LineItem e WHERE e.itemId = :itemId AND e.customerOrder.orderId = :orderId")
})
public class LineItem implements Serializable {
	private static final long serialVersionUID = 1133993844977595333L;
	
	public static final String FIND_ALL = "LineItem.FIND_ALL";
	public static final String FIND_BY_ORDER_ID = "LineItem.FIND_BY_ORDER_ID";
	public static final String FIND_BY_ORDER_N_ITEM_ID = "LineItem.FIND_BY_ORDER_N_ITEM_ID";
	
	@Id
	private int itemId;
	
	private int quantity;

	@ManyToOne
	@JoinColumn(name="vendorPartNumber")
	private VendorPart vendorPart;
	
	@Id
	@ManyToOne
	@JoinColumn(name="orderId")
	private CustomerOrder customerOrder;
	
	public LineItem() { }
	
	public LineItem(CustomerOrder order, int quantity, VendorPart vendorPart) {
		super();
		this.quantity = quantity;
		this.vendorPart = vendorPart;
		this.customerOrder = order;
		this.itemId = order.nextId();
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public VendorPart getVendorPart() {
		return vendorPart;
	}
	public void setVendorPart(VendorPart vendor) {
		this.vendorPart = vendor;
	}
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}
	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
}
