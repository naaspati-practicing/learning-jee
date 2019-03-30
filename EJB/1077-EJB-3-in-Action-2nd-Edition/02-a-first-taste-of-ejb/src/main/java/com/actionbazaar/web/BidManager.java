package com.actionbazaar.web;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.actionbazaar.buslogic.api.BidService;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Item;

@Model
public class BidManager {
	@EJB private BidService bidService;
	@EJB private Bidder bidder;
	
	@Inject
	private Item item;
	private Bid bid = new Bid();
	
	@Produces
	@Named
	@RequestScoped
	public Bid getBid() {
		return bid;
	}
	
	public String placeBid() {
		bid.setBidder(bidder);
		bid.setItem(item);
		
		// Incomplete bidService.addBid(bid);
        return "bid_confirm.xhtml";
	}
	

}
