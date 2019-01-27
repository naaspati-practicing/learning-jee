package sam.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;


@Entity
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private long ccNumber;
	private Customer customer;
	
	@OrderColumn
	@OneToMany
	private List<CardTransaction> transactionHistory;

	public List<CardTransaction> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<CardTransaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	public long getCcNumber() {
		return ccNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}

