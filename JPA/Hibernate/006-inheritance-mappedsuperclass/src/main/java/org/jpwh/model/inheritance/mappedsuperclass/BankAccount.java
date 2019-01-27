package org.jpwh.model.inheritance.mappedsuperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount extends BillingDetails {
	@Id @GeneratedValue
	private long id;
	
	@NotNull
	private String account;
	
	@NotNull
	private String backname;
	
	@NotNull
	private String swift;
	
	public BankAccount() {}
	
	public BankAccount(String owner, String account, String backname, String swift) {
		super(owner);
		this.account = account;
		this.backname = backname;
		this.swift = swift;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBackname() {
		return backname;
	}
	public void setBackname(String backname) {
		this.backname = backname;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public long getId() {
		return id;
	}
}
