package jeetutorial.dukesbookstore.jsf.listeners;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import jeetutorial.dukesbookstore.Utils;

public class LinkBookChangeListener implements ActionListener, Utils {
	private final Data data = new Data();
	
	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		String current = event.getComponent().getId();
		Long book_id = data.get(current);
		sessionMap().put(KEY_BOOK_ID, book_id);
	}
	

}
