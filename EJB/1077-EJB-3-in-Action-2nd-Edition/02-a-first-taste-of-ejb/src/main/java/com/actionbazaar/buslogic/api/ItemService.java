package com.actionbazaar.buslogic.api;

import javax.ejb.Local;

import com.actionbazaar.persistence.Item;

@Local
public interface ItemService {
	Item getItem(long item_id);
	void addItem(Item item);
}