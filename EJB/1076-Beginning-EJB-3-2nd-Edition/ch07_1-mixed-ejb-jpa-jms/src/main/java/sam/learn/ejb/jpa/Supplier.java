package sam.learn.ejb.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CH07_SUPPLIER")
@NamedQuery(name=Supplier.NAMED_QUERY_FIND_ALL, query="select o from Supplier o")
public class Supplier extends BusinessContact {
	private static final long serialVersionUID = -2363494974504808729L;
	public static final String NAMED_QUERY_FIND_ALL = "Supplier.findAll";

	@ManyToOne
	@JoinColumn(name="PAYMENT_ADDRESS")
	private Address paymentAddress;

	@ManyToMany(mappedBy="suppliers")
	private List<Item> items;

	public Address getPaymentAddress() { return paymentAddress; }
	public void setPaymentAddress(Address paymentAddress) { this.paymentAddress = paymentAddress; }

	public List<Item> getItems() { return items; }
	public void setItems(List<Item> items) { this.items = items; }
	
	public void addItem(Item item) {
		getItems().add(item);
		item.addSupplier(this);
	}
	public void removeItem(Item item) {
		if(getItems().remove(item))
			item.removeSupplier(this);
	}


}
