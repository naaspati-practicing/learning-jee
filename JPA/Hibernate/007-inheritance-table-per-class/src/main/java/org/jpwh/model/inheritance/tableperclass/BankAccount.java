package org.jpwh.model.inheritance.tableperclass;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount extends BillingDetails {
	
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
}
