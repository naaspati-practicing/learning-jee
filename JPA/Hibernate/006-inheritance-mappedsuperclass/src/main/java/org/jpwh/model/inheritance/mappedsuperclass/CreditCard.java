package org.jpwh.model.inheritance.mappedsuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@AttributeOverride(name="owner", column=@Column(name="CC_OWNER", nullable=false))
public class CreditCard extends BillingDetails {
	@Id @GeneratedValue
	private long id;
	
	@NotNull private String cardNumber;
	@NotNull private String expMonth;
	@NotNull private String expYear;
	
	public CreditCard() {
		super();
	}

	public CreditCard(String owner, String cardNumber, String expMonth, String expYear) {
		super(owner);
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", cardNumber=" + cardNumber + ", expMonth=" + expMonth + ", expYear=" + expYear
				+ ", getOwner()=" + getOwner() + "]";
	}
	
}
