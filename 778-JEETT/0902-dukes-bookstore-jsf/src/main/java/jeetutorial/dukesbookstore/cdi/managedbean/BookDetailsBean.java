package jeetutorial.dukesbookstore.cdi.managedbean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jeetutorial.dukesbookstore.jpa.entities.Book;

@Named("details")
@SessionScoped
public class BookDetailsBean extends AbstractBean {
	private static final long serialVersionUID = -4150955144160524750L;
	
	public String add() {
		Book book = getSelected();
		getCart().add(book);
		message(null, MSG_CONFIRM_ADD, book.getTitle());
		
		return PATH_BOOK_CATALOG;
	}

}
