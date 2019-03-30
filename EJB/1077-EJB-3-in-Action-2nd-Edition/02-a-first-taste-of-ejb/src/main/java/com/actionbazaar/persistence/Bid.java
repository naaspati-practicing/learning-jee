package com.actionbazaar.persistence;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BIDS")
public class Bid extends BaseEntity {
	private static final long serialVersionUID = 5633146894789640390L;
	
	@Column(name = "BID_DATE", nullable=false)
	private LocalDateTime bidDate;
	
	@Column(name = "BID_PRICE", nullable=false)
	private double bidpPrice;

	@ManyToOne
	@JoinColumn(name="ITEM_ID")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name="BIDDER_ID")
	private Bidder bidder;
	
	public Bid() { }
	
	public LocalDateTime getBidDate(){ return this.bidDate; }
	public void setBidDate(LocalDateTime bidDate){ this.bidDate = bidDate; }

	public double getBidpPrice(){ return this.bidpPrice; }
	public void setBidpPrice(double bidpPrice){ this.bidpPrice = bidpPrice; }

	public Item getItem(){ return this.item; }
	public void setItem(Item item){ this.item = item; }

	public Bidder getBidder(){ return this.bidder; }
	public void setBidder(Bidder bidder){ this.bidder = bidder; }

}
