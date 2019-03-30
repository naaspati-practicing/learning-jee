package com.actionbazaar.buslogic.api;

import java.util.List;

import javax.ejb.Local;

import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Billing;
import com.actionbazaar.persistence.Item;
import com.actionbazaar.persistence.Order;
import com.actionbazaar.persistence.Shipping;

@Local
public interface OrderProcessor {
	Bidder getBidder();
	void setBidder(Bidder bidder);
	List<Billing> getBillingHistory();
	List<Shipping> getShippingHistory();
	Item getItem();
	void setItem(Item item);
	Shipping getShipping();
	void setShipping(Shipping shipping);
	Billing getBilling();
	void setBilling(Billing billing);
	List<Billing> getBillingChoices();
	void setBillingChoices(List<Billing> billingChoices);
	void placeOrder();
	void saveOrder(Order order);
	void notifyBillingFailed(Order order);
	void notifyBillingSuccess(Order order);
	void bill(Order order) throws BillingException;
	double computeShippingCost(Shipping shipping, Item item);
	void updateBillingHistory(Bidder bidder, Billing shipping);
	void updateShippingHistory(Bidder bidder, Shipping shipping);
	List<Shipping> getShippingChoices();
	void setShippingChoices(List<Shipping> shippingChoices);
	List<Billing> filterBillingHistory(List<Billing> list, Item item);
	List<Shipping> filterShippingHistory(List<Shipping> list, Item item);
}