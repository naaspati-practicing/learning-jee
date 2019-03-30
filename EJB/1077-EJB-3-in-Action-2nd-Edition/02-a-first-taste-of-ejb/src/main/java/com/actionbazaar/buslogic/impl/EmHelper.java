package com.actionbazaar.buslogic.impl;

import javax.persistence.EntityManager;

public abstract class EmHelper<T> {
	protected abstract Class<T> clazz();
	protected abstract EntityManager em();
	
	protected T find(long id) {
		return em().find(clazz(), id);
	}
	protected void remove(T t) {
		em().remove(em().merge(t));
	}
}
