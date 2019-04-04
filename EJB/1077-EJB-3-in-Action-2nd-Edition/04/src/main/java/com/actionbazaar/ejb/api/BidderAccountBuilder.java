package com.actionbazaar.ejb.api;

import javax.ejb.Remote;

import com.actionbazaar.jaxb.Bidder;
import com.actionbazaar.jaxb.BillingInfo;
import com.actionbazaar.jaxb.BiographicalInfo;
import com.actionbazaar.jaxb.LoginInfo;

@Remote
public interface BidderAccountBuilder {
	void setLoginInfo(LoginInfo loginInfo);
	void setBiographicalInfo(BiographicalInfo biographicalInfo);
	void setBillingInfo(BillingInfo billingInfo);
	void cancel();
	Bidder build();
}