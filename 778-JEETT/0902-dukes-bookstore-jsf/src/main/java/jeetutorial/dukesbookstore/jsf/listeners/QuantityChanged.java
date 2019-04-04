package jeetutorial.dukesbookstore.jsf.listeners;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import jeetutorial.dukesbookstore.Utils;

public class QuantityChanged implements ValueChangeListener, Utils {

	@Override
	public void processValueChange(ValueChangeEvent e) throws AbortProcessingException {
		Object o = e.getNewValue();
		sessionMap().put(KEY_CHANGED, o != null);
	}
}
