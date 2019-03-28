package sam.learn.ejb.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "CH03_CUSTOMER_ORDER")
@NamedQueries({
	@NamedQuery(name=CustomerOrder.NAMED_QUERY_FIND_ALL, query="select o from CustomerOrder o"),
	@NamedQuery(name=CustomerOrder.NAMED_QUERY_FIND_BY_EMAIL, query="select o from CustomerOrder o where o.customer.email = :email")
})
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = 1812507861683099952L;

	public static final String NAMED_QUERY_FIND_ALL = "CustomerOrder.findAll";
	public static final String NAMED_QUERY_FIND_BY_EMAIL = "CustomerOrder.findByEmail";
	
	@Id
	@Column(nullable=false)
	@GeneratedValue
	private int id;
	
	@Version
	private int version;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Enumerated
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getVersion() { return version; }
	public void setVersion(int version) { this.version = version; }

	public Date getCreationDate() { return creationDate; }
	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	public OrderStatus getStatus() { return status; }
	public void setStatus(OrderStatus status) { this.status = status; }

	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
}
