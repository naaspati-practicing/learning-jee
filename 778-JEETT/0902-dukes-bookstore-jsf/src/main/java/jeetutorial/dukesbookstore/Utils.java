package jeetutorial.dukesbookstore;

import java.util.Map;

import javax.faces.context.FacesContext;

public interface Utils {
	final String KEY_BOOK_ID = "book_id";
	final String KEY_SELECTED = "selected";
	final String KEY_BOOK = "book";
	final String KEY_ITEM = "item";
	final String KEY_NAME = "name";
	final String KEY_CHANGED = "changed";
	
	final String PATH_BOOK_CATALOG = "bookcatalog";
	final String PATH_BOOK_CASHIER = "bookcashier";
	final String PATH_BOOK_ORDER_ERROR = "bookordererror";
	final String PATH_BOOK_RECEIPT = "bookreceipt";
	final String PATH_BOOK_DETAILS = "bookdetails";
	
	default FacesContext context() {
		return FacesContext.getCurrentInstance();
	}
	default Map<String, Object> sessionMap() {
		return context().getExternalContext().getSessionMap();
	}
	default Map<String, String> requestParamMap() {
		return context().getExternalContext().getRequestParameterMap();
	}
}
