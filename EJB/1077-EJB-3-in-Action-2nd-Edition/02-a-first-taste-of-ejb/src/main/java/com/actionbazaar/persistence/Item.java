package com.actionbazaar.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ITEMS")
public class Item extends BaseEntity {
	private static final long serialVersionUID = -7371488916800014500L;

	@Column(nullable=false) 
	private String name;

	@Column(name="BID_START_DATE", nullable=false) 
	private LocalDateTime bidStartDate;

	@Column(name="BID_END_DATE", nullable=false) 
	private LocalDateTime bidEndDate;

	@Column(name="CREATION_DATE", nullable=false) 
	private LocalDateTime creationDate;

	@Column(name="INITIAL_PRICE", nullable=false) 
	private double initialPrice;

	@OneToMany(mappedBy="item", cascade=CascadeType.ALL)
	private List<Bid> bids;
	
	public Item() { }
	
	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }

	public LocalDateTime getBidStartDate(){ return this.bidStartDate; }
	public void setBidStartDate(LocalDateTime bidStartDate){ this.bidStartDate = bidStartDate; }

	public LocalDateTime getBidEndDate(){ return this.bidEndDate; }
	public void setBidEndDate(LocalDateTime bidEndDate){ this.bidEndDate = bidEndDate; }


	public LocalDateTime getCreationDate(){ return this.creationDate; }
	public void setCreationDate(LocalDateTime creationDate){ this.creationDate = creationDate; }

	public double getInitialPrice(){ return this.initialPrice; }
	public void setInitialPrice(double initialPrice){ this.initialPrice = initialPrice; }

	public List<Bid> getBids() { return bids; }
	public void setBids(List<Bid> bids) { this.bids = bids; }

	public void addBid(Bid bid) { 
		getBids().add(Objects.requireNonNull(bid));
		bid.setItem(this);
	}
	public boolean removeBid(Bid bid) { 
		if(bid == null)
			return false;
		
		if(getBids().remove(bid)) {
			bid.setItem(null);
			return true;
		} else 
			return false;
	}



}
