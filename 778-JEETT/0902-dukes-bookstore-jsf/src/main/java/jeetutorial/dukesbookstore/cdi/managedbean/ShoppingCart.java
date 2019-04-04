package jeetutorial.dukesbookstore.cdi.managedbean;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jeetutorial.dukesbookstore.jpa.entities.Book;

@Named("cart")
@SessionScoped
public class ShoppingCart extends AbstractBean {
	private static final long serialVersionUID = 1065299327735462617L;
	
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);
	private final Map<Long, ShoppingCartItem> items = new ConcurrentHashMap<>();
	
	public void add(Book book) {
		long id = book.getId();
		
		if(id == 0)
			throw new IllegalStateException("unpersisted book: "+book);
		
		items.computeIfAbsent(id, d -> new ShoppingCartItem(book)).increamentQuantity();
	} 
	public synchronized boolean remove(long book_id) {
		ShoppingCartItem itm = items.get(book_id);
		if(itm == null)
			return false;
		
		itm.decreamentQuantity();
		
		if(itm.getQuantity() == 0)
			items.remove(book_id);
		
		return true;
	}
	
	public Collection<ShoppingCartItem> getItems() {
		return Collections.unmodifiableCollection(items.values());
	}
	public int getItemsCount() {
		return items.isEmpty() ? 0 : items.values().stream().mapToInt(ShoppingCartItem::getQuantity).sum();
	}
	public double getTotalPrice() {
		if(items.isEmpty() )
			return 0;
		
		double price = items.values().stream().mapToDouble(d -> d.getItem().getPrice() * d.getQuantity()).sum();
		return ((long)(price * 100))/100d;
	}
	public String buy() {
		if(getItemsCount() < 1) {
			message(null, "Empty Cart");
			return null;
		} else {
			return PATH_BOOK_CASHIER;
		}
	}
	public void clear() {
		items.clear();
	}
	public int getItemQuentity(Book book) {
		return items.get(book.getId()).getQuantity();
	}
}
