package jeetutorial.dukesbookstore.cdi.managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import jeetutorial.dukesbookstore.Utils;
import jeetutorial.dukesbookstore.jpa.entities.Book;
import jeetutorial.dukesbookstore.view.messages.Messages;

@SessionScoped
@Named
public class AbstractBean implements Serializable, Utils {
	private static final long serialVersionUID = -8283052118875753191L;
	
	public static final String MSG_CONFIRM_ADD = "ConfirmAdd";
	
	@Inject private ShoppingCart cart;
	@Inject private Messages messages;
	
	public ShoppingCart getCart() {
		return cart;
	}

	protected void message(String clientId, String key, Object...params) {
		if(params == null || params.length == 0)
			message(clientId, key);
		else {
			String  s;
			try {
				s = messages.format(key, context().getViewRoot().getLocale(), params);
			} catch (Exception e) {
				s = "??? "+key+" ???"; 
			}
			context().addMessage(clientId, new FacesMessage(s));
		}
	}
	
	protected void message(String clientId, String key) {
		String  s;
		try {
			s = messages.get(key, context().getViewRoot().getLocale());
		} catch (Exception e) {
			s = "??? "+key+" ???"; 
		}
		context().addMessage(clientId, new FacesMessage(s));		
	}
	protected Book getSelected() {
		return (Book) sessionMap().get(KEY_SELECTED);
	}
	
	protected String addToCart(Book book) {
		cart.add(book);
		
		message(null, BookDetailsBean.MSG_CONFIRM_ADD, book.getTitle());
		return BookDetailsBean.PATH_BOOK_CATALOG;
	}
}
