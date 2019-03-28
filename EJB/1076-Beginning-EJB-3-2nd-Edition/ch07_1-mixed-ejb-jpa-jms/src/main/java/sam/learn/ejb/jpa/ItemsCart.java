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
@Table(name = "CH07_ITEM_CART")
@NamedQuery(name=ItemsCart.NAMED_QUERY_FIND_ALL, query="select o from ItemsCart o")
public class ItemsCart extends Items {
	private static final long serialVersionUID = -5223589622571663709L;
	public static final String NAMED_QUERY_FIND_ALL = "ItemsCart.findAll";

	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	public ItemsCart() { }

	public ItemsCart(int quantity, Item item) {
		setQuantity(quantity);
		setCreatedDate(new Date());
		setItem(item);
	}
	
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }

	public Date getCreatedDate() { return createdDate; }
	public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
