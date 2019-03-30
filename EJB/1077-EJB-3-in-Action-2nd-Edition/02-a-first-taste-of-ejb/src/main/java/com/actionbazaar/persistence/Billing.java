package com.actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;

public class Billing implements Serializable { 
	private static final long serialVersionUID = 4856934434853575695L;
	
	@Column(name="ACC_NUM", nullable=false)
	private String accountNumber;
	
	@Column(name="EXPIRY_DATE", nullable=false)
	private String expiryDate;
	
	@Column(name="SECRET_CODE", nullable=false)
	private String secretCode;
	
	private Address address;
	
	public String getAccountNumber(){ return this.accountNumber; }
	public void setAccountNumber(String accountNumber){ this.accountNumber = accountNumber; }

	public String getExpiryDate(){ return this.expiryDate; }
	public void setExpiryDate(String expiryDate){ this.expiryDate = expiryDate; }

	public String getSecretCode(){ return this.secretCode; }
	public void setSecretCode(String secretCode){ this.secretCode = secretCode; }

	public Address getAddress(){ return this.address; }
	public void setAddress(Address address){ this.address = address; }
}
