package com.actionbazaar.ejb.api;

import javax.ejb.Remote;

import com.actionbazaar.jaxb.BillingInfo;
import com.actionbazaar.jaxb.BiographicalInfo;
import com.actionbazaar.jaxb.LoginInfo;

@Remote
public interface BidderAccountCreator {
	void add(LoginInfo info);
	void add(BiographicalInfo info);
	void add(BillingInfo info);
	
	/**
     * Cancel account creation
     */
	void cancel();
	/**
     * Creates a account
     */
	void create();
}
