package com.actionbazaar.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bid implements Serializable {
	private static final long serialVersionUID = -1425304509827003932L;
	
	private long id;
	private double bidPrice;
	private Item item;
	private Bidder bidder;
	
	public long getId(){ return this.id; }
	public void setId(long id){ this.id = id; }

	public double getBidPrice(){ return this.bidPrice; }
	public void setBidPrice(double bidPrice){ this.bidPrice = bidPrice; }

	public Item getItem(){ return this.item; }
	public void setItem(Item item){ this.item = item; }

	public Bidder getBidder(){ return this.bidder; }
	public void setBidder(Bidder bidder){ this.bidder = bidder; }
	
}
