package com.actionbazaar.jaxb;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bidder extends User {
	private static final long serialVersionUID = -2391005062723409153L;
	
	private long creditRating;
	private Set<Bid> bids;
	
	public long getCreditRating(){ return this.creditRating; }
	public void setCreditRating(long creditRating){ this.creditRating = creditRating; }

	public Set<Bid> getBids(){ return this.bids; }
	public void setBids(Set<Bid> bids){ this.bids = bids; }
	
}
