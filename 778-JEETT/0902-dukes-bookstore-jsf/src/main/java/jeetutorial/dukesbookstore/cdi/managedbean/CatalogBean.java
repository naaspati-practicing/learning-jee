package jeetutorial.dukesbookstore.cdi.managedbean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jeetutorial.dukesbookstore.jpa.entities.Book;

@Named("catlog")
@SessionScoped
public class CatalogBean extends AbstractBean {
	private static final long serialVersionUID = 3892425546450573412L;
	
	protected Book book() {
		return (Book) sessionMap().get(KEY_BOOK);
	}
	
	public String add() {
		return addToCart(book());
	}
	public String details() {
		sessionMap().put(KEY_SELECTED, book());
		return PATH_BOOK_DETAILS;
	}
	public int getBooksCount() {
		return getCart().getItemsCount();
	}
	public int getItemQuentity() {
		return getCart().getItemQuentity(book());
	}
}
