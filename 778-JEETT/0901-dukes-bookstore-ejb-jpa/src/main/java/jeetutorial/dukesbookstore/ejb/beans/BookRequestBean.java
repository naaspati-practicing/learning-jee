package jeetutorial.dukesbookstore.ejb.beans;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jeetutorial.dukesbookstore.ejb.exception.BooksNotFoundException;
import jeetutorial.dukesbookstore.ejb.exception.OrderException;
import jeetutorial.dukesbookstore.ejb.exception.OutOfStockException;
import jeetutorial.dukesbookstore.jpa.entities.Book;
import jeetutorial.dukesbookstore.jpa.entities.ItemCount;

@Stateful
public class BookRequestBean {
	@PersistenceContext
	private EntityManager em;
	
	public void persist(List<Book> book) {
		try {
			for (Book b : book) 
				em.persist(b);	
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	public void persist(Book book) {
		try {
			em.persist(book);
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
	public List<Book> getAllBooks() throws BooksNotFoundException {
		try {
			return em.createNamedQuery(Book.NAMED_QUERY_FIND_ALL, Book.class).getResultList();
		} catch (Exception e) {
			throw new BooksNotFoundException("failed to get books", e);
		}
	}
	public Book getBook(long id) throws BooksNotFoundException {
		Book b = em.find(Book.class, id);

		if(b == null)
			throw new BooksNotFoundException("no book found for: "+id);
		else
			return b;
	}

	public void buyBook(long book_id, int quantity) throws OrderException {
		Book book = em.find(Book.class, book_id);
		if(book == null)
			throw new NoSuchElementException("no book found with book_id: "+book_id);
		
		int remaining = book.getInventory();
		if(remaining <  quantity)
			throw new OutOfStockException("not enough items(Book["+book_id+"]) in order to complete the order.");
		else 
			book.setInventory(remaining - quantity);
	}

	public void buyAll(Iterable<ItemCount> items) throws OrderException {
		try {
			for (ItemCount t : items) {
				buyBook(((Book)t.getItem()).getId(), t.getQuantity());
			}
		} catch (OrderException ex) {
			throw new OrderException("Inventory update failed: " + ex.getMessage());
		}
	}
}
