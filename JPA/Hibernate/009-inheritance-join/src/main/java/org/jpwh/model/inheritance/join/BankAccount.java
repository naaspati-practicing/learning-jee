package org.jpwh.model.inheritance.join;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount extends BillingDetails {
	
	@NotNull private String account;
	@NotNull private String bankname;
	@NotNull private String swift;
	
	public BankAccount() {}
	
	public BankAccount(String owner, String account, String bankname, String swift) {
		super(owner);
		this.account = account;
		this.bankname = bankname;
		this.swift = swift;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBackname() {
		return bankname;
	}
	public void setBackname(String backname) {
		this.bankname = backname;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}

	@Override
	public String toString() {
		return "BankAccount [account=" + account + ", bankname=" + bankname + ", swift=" + swift + ", owner=" + owner
				+ "]";
	}
}
