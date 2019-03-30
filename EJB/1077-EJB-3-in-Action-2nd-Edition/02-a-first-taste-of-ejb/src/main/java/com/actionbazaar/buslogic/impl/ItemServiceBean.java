package com.actionbazaar.buslogic.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.actionbazaar.buslogic.api.ItemService;
import com.actionbazaar.persistence.Item;

@Stateless
public class ItemServiceBean extends EmHelper<Item> implements ItemService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager em() {
		return em;
	}
	@Override
	protected Class<Item> clazz() {
		return Item.class;
	}
	
	@Override
	public Item getItem(long item_id) {
		return find(item_id);
	}
	public void addItem(Item item) {
		em.persist(item);
	} 
}
