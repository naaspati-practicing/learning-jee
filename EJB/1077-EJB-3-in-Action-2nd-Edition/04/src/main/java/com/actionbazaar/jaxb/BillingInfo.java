package com.actionbazaar.jaxb;

import java.io.Serializable;

public class BillingInfo implements Serializable {
	private static final long serialVersionUID = -8798525159343738790L;
	
	private String accountNumber;
	private String creditCardType;
	private String expiryDate;
	
	public String getAccountNumber(){ return this.accountNumber; }
	public void setAccountNumber(String accountNumber){ this.accountNumber = accountNumber; }

	public String getCreditCardType(){ return this.creditCardType; }
	public void setCreditCardType(String creditCardType){ this.creditCardType = creditCardType; }

	public String getExpiryDate(){ return this.expiryDate; }
	public void setExpiryDate(String expiryDate){ this.expiryDate = expiryDate; }
}
