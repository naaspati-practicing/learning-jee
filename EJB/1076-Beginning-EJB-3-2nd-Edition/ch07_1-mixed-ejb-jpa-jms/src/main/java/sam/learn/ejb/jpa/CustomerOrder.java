package sam.learn.ejb.jpa;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CH07_CUSTOMER_ORDER")
@NamedQuery(name=CustomerOrder.NAMED_QUERY_FIND_ALL, query="select o from CustomerOrder o")
public class CustomerOrder extends BaseEntity {
	private static final long serialVersionUID = 8960531819014373849L;

	public static final String NAMED_QUERY_FIND_ALL = "CustomerOrder.findAll";

	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@OneToMany(mappedBy="customerOrder", cascade=CascadeType.ALL)
	private List<ItemsOrder> orderItems;

	public CustomerOrder() { }

	public CustomerOrder(String status) {
		setCreationDate(new Date());
		setStatus(status);
	}

	public Date getCreationDate() { return creationDate; }
	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }

	public List<ItemsOrder> getOrderItems() { return orderItems; }
	public void setOrderItems(List<ItemsOrder> orderItems) { this.orderItems = orderItems; }

	public void addOrderItem(ItemsOrder item) {
		Objects.requireNonNull(item);
		getOrderItems().add(item);
		item.setCustomerOrder(this);
	}
	public void removeOrderItem(ItemsOrder item) {
		if(item == null)
			return;

		if(getOrderItems().remove(item))
			item.setCustomerOrder(null);
	}
}
