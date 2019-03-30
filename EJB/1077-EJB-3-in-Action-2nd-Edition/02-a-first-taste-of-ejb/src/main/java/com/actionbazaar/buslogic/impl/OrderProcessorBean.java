package com.actionbazaar.buslogic.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.actionbazaar.buslogic.api.BillingException;
import com.actionbazaar.buslogic.api.OrderProcessor;
import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Billing;
import com.actionbazaar.persistence.Item;
import com.actionbazaar.persistence.Order;
import com.actionbazaar.persistence.OrderStatus;
import com.actionbazaar.persistence.Shipping;

@Stateful
public class OrderProcessorBean implements OrderProcessor, Serializable {
	private static final long serialVersionUID = -3315296144975017072L;
	
	private Bidder bidder;
	private Item item;
	private Shipping shipping;
	private Billing billing;

	private List<Shipping> shippingChoices;
	private List<Billing> billingChoices;

	public OrderProcessorBean() {
		this.shippingChoices = list(new Shipping());
		this.billingChoices = list(new Billing());
	}
	private static <E> List<E> list(E t) {
		List<E> list = new LinkedList<>();
		list.add(t);
		return list;
	}

	@Override
	public Bidder getBidder(){ return this.bidder; }
	@Override
	public void setBidder(Bidder bidder){ 
		this.bidder = bidder;
		this.shippingChoices = getShippingHistory();
		this.billingChoices = getBillingHistory();
	}

	@Override
	public List<Billing> getBillingHistory() {
		return list(new Billing());
	}
	@Override
	public List<Shipping> getShippingHistory() {
		return list(new Shipping());
	}

	@Override
	public Item getItem(){ return this.item; }
	@Override
	public void setItem(Item item){ 
		this.item = item;
		this.shippingChoices = filterShippingHistory(shippingChoices, item);
		this.billingChoices = filterBillingHistory(billingChoices, item);
	}

	@Override
	public Shipping getShipping(){ return this.shipping; }
	@Override
	public void setShipping(Shipping shipping){ 
		this.shipping = shipping;
		updateShippingHistory(bidder, shipping);
		this.shipping.setCost(computeShippingCost(shipping, item));
	}
	
	@Override
	public Billing getBilling(){ return this.billing; }
	@Override
	public void setBilling(Billing billing){ 
		this.billing = billing;
		updateBillingHistory(bidder, billing);
	}

	@Override
	public List<Billing> getBillingChoices(){ return this.billingChoices; }
	@Override
	public void setBillingChoices(List<Billing> billingChoices){ this.billingChoices = billingChoices; }

	@Override
	@Asynchronous
	@Remove
	public void placeOrder() {
		Order order = new Order();
		order.setBidder(bidder);
		order.setItem(item);
		order.setShipping(shipping);
		order.setBilling(billing);
		
		try {
			bill(order);
			notifyBillingSuccess(order);
			order.setOrderStatus(OrderStatus.COMPLETE);
		} catch (BillingException e) {
			notifyBillingFailed(order);
			order.setOrderStatus(OrderStatus.BILLING_FAILED);
		} finally {
			saveOrder(order);
		}
	}
	
	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyBillingFailed(Order order) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyBillingSuccess(Order order) {
		// TODO Auto-generated method stub
	}
	@Override
	public void bill(Order order) throws BillingException {
		throw new BillingException();
	}
	@Override
	public double computeShippingCost(Shipping shipping, Item item) {
		return 0; // TODO Auto-generated method stub
	}

	@Override
	public void updateBillingHistory(Bidder bidder, Billing shipping) { }
	@Override
	public void updateShippingHistory(Bidder bidder, Shipping shipping) { }

	@Override
	public List<Shipping> getShippingChoices(){ return this.shippingChoices; }
	@Override
	public void setShippingChoices(List<Shipping> shippingChoices){ this.shippingChoices = shippingChoices; }

	@Override
	public List<Billing> filterBillingHistory(List<Billing> list, Item item) {
		return list;
	}
	@Override
	public List<Shipping> filterShippingHistory(List<Shipping> list, Item item) {
		return list;
	}

}
