package com.actionbazaar.buslogic.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.actionbazaar.buslogic.api.UserService;
import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.User;

@Stateless
public class UserServiceBean extends EmHelper<Bidder> implements UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected Class<Bidder> clazz() {
		return Bidder.class;
	}
	@Override
	protected EntityManager em() {
		return em;
	}
	
	@Override
	public Bidder getUser(long id) {
		return find(id);
	}

	@Override
	public void addUser(User bidder) {
		em.persist(bidder);
	}

}
