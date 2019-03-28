package sam.learn.ejb.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CH07_ITEM_ORDER")
@NamedQuery(name=ItemsOrder.NAMED_QUERY_FIND_ALL, query="select o from ItemsOrder o")
public class ItemsOrder extends Items {
	private static final long serialVersionUID = -8607350182769648917L;
	public static final String NAMED_QUERY_FIND_ALL = "ItemsOrder.findAll";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SHIP_DATE")
	private Date shipDate;

	private float price;
	private String status;

	@ManyToOne
	@JoinColumn(name="CUSTOMER_ORDER_ID")
	private CustomerOrder customerOrder;

	public ItemsOrder() { }

	public ItemsOrder(int quantity, Item item, Date orderDate, float price, Date shipDate, String status) {
		super(quantity, item);
		setOrderDate(orderDate);
		setPrice(price);
		setShipDate(shipDate);
		setStatus(status);
	}

	public Date getOrderDate() { return orderDate; }
	public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

	public Date getShipDate() { return shipDate; }
	public void setShipDate(Date shipDate) { this.shipDate = shipDate; }

	public float getPrice() { return price; }
	public void setPrice(float price) { this.price = price; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public CustomerOrder getCustomerOrder() { return customerOrder; }
	public void setCustomerOrder(CustomerOrder customerOrder) { this.customerOrder = customerOrder; }
}
