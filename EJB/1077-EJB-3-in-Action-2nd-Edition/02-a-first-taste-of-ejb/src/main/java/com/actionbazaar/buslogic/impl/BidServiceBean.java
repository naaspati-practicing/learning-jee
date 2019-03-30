package com.actionbazaar.buslogic.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.actionbazaar.buslogic.api.BidService;
import com.actionbazaar.persistence.Bid;

@Stateless
public class BidServiceBean extends EmHelper<Bid> implements BidService {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Override
	protected EntityManager em() {
		return em;
	}
	@Override
	protected Class<Bid> clazz() {
		return Bid.class;
	}
	@Override
	public void addBid(Bid bid) {
		em.persist(bid);
	}
}
