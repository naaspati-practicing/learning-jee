package jeetutorial.dukesbookstore.cdi.managedbean;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;

import jeetutorial.dukesbookstore.ejb.beans.BookRequestBean;
import jeetutorial.dukesbookstore.ejb.exception.BooksNotFoundException;
import jeetutorial.dukesbookstore.jpa.entities.Book;

@Named("store")
@SessionScoped
public class BookstoreBean extends AbstractBean {
	private static final long serialVersionUID = -1478716841140257308L;
	
	private Book featured;
	protected String title;

	@EJB private BookRequestBean bookRequestBean;

	public Book getFeatured() {

		if(featured == null) {
			try {
				featured = Optional.of(bookRequestBean.getAllBooks())
						.filter(m -> !m.isEmpty())
						.map(m -> m.get(Math.min(4, m.size() -1)))
						.orElseThrow(() -> new BooksNotFoundException());
			} catch (BooksNotFoundException|NullPointerException e) {
				throw new FacesException("Could not get books: ", e);
			}
		}

		return featured;
	}
	public String add() {
		return addToCart(getFeatured());
	}
	public String addSelected() {
		try {
			return addToCart(getSelectedByBookId());
		} catch (BooksNotFoundException| NullPointerException e) {
			throw new FacesException("Could not get book: ", e);
		}
	}
	private Book getSelectedByBookId() throws BooksNotFoundException {
		Long book_id = (Long) sessionMap().get(KEY_BOOK_ID);
		return bookRequestBean.getBook(book_id);
	}
	public String details() {
		sessionMap().put(KEY_SELECTED, getFeatured());
		return PATH_BOOK_DETAILS;
	}
	public String selectedDetails() {
		try {
			Book b = getSelectedByBookId();
			sessionMap().put(KEY_SELECTED, b);	
		} catch (BooksNotFoundException|NullPointerException e) {
			throw new FacesException("Could not get book: ", e);
		}
		return PATH_BOOK_DETAILS;
	}
	public String getSelectedTitle() {
		try {
			return getSelectedByBookId().getTitle();
		} catch (BooksNotFoundException|NullPointerException e) {
			throw new FacesException("Could not get book: ", e);
		}
	}
	public List<Book> getBooks() {
		try {
			return bookRequestBean.getAllBooks();
		} catch (BooksNotFoundException|NullPointerException e) {
			throw new FacesException("Could not get book: ", e);
		}
	}
}
