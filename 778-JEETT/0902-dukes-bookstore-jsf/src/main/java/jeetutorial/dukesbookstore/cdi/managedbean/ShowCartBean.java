package jeetutorial.dukesbookstore.cdi.managedbean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import jeetutorial.dukesbookstore.jpa.entities.Book;

@Named("showcart")
@SessionScoped
public class ShowCartBean extends AbstractBean {
	private static final long serialVersionUID = -1075383577551940882L;
	
	private boolean cartChanged = false;
	
	public boolean isCartChanged() {
		return cartChanged;
	}
	public void setCartChanged(boolean cartChanged) {
		this.cartChanged = cartChanged;
	}
	
	protected ShoppingCartItem item() {
		return (ShoppingCartItem) sessionMap().get(KEY_ITEM);
	}
	
	public String details() {
		sessionMap().put(KEY_SELECTED, item().getItem());
		return PATH_BOOK_DETAILS;
	}

    public String remove() {
        Book book = (Book) item().getItem();
        getCart().remove(book.getId());
        setCartChanged(true);
        message(null, "ConfirmRemove", new Object[]{book.getTitle()});

        return null;
    }
    public String update() {
        String changed = (String) sessionMap().get("changed");
        
        if ((changed != null) && changed.equals("true")) {
            setCartChanged(true);
        } else {
            setCartChanged(false);
        }
        if (isCartChanged()) {
            message(null, "QuantitiesUpdated");
        } else {
            message(null, "QuantitiesNotUpdated");
        }
        sessionMap().put("changed", "false");
        return (null);
    }

}
