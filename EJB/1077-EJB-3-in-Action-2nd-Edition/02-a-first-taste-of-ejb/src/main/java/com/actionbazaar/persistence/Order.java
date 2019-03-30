package com.actionbazaar.persistence;

import java.io.Serializable;

public class Order implements Serializable { 
	private static final long serialVersionUID = 4764176718559735250L;
	
	private Bidder bidder;
	private Item item;
	private Shipping shipping;
	private Billing billing;
	private OrderStatus orderStatus;

	public Bidder getBidder(){ return this.bidder; }
	public void setBidder(Bidder bidder){ this.bidder = bidder; }

	public Item getItem(){ return this.item; }
	public void setItem(Item item){ this.item = item; }

	public Shipping getShipping(){ return this.shipping; }
	public void setShipping(Shipping shippingCost){ this.shipping = shippingCost; }

	public Billing getBilling(){ return this.billing; }
	public void setBilling(Billing billing){ this.billing = billing; }

	public OrderStatus getOrderStatus(){ return this.orderStatus; }
	public void setOrderStatus(OrderStatus orderStatus){ this.orderStatus = orderStatus; }

}
